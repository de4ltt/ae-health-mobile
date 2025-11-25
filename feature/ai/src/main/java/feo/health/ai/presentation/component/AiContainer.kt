package feo.health.ai.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feo.health.ai.presentation.model.request.FeatureDiseaseRequest
import feo.health.ai.presentation.model.request.FeatureProcedureRequest
import feo.health.ai.presentation.model.request.FeatureSuggestionRequest
import feo.health.ai.presentation.viewmodel.companion.AiEvent
import feo.health.ai.presentation.viewmodel.companion.AiState
import feo.health.ai.presentation.viewmodel.companion.SearchOption
import feo.health.ui.component.HButton
import feo.health.ui.component.HProgressIndicator
import feo.health.ui.component.HText
import feo.health.ui.component.HTextBar
import feo.health.ui.component.container.HContainer
import feo.health.ui.component.container.HOptionSelector
import feo.health.ui.resource.HIcons
import feo.health.ui.resource.HStrings
import feo.health.ui.resource.HStrings.capitalize
import feo.health.ui.theme.HTheme
import feo.health.ui.util.ILoading
import kotlinx.coroutines.flow.StateFlow

object AiContainer : ILoading {

    @Composable
    fun Default(
        modifier: Modifier = Modifier,
        state: StateFlow<AiState>,
        onEvent: (AiEvent) -> Unit,
        onTextClick: (tag: String, name: String) -> Unit
    ) {

        val aiState by state.collectAsStateWithLifecycle()

        var selectedOption by remember { mutableStateOf(SearchOption.PROCEDURE) }

        val hintText = stringResource(selectedOption.hintText).capitalize()

        val isSymptomsVisible = selectedOption == SearchOption.DISEASE

        val symptomTags = remember { mutableStateListOf<String>() }

        val searchBarEnabled =
            aiState is AiState.Default && selectedOption != SearchOption.DISEASE || aiState is AiState.Found
        val isInfoShown = aiState is AiState.Found

        val textFieldState = rememberTextFieldState()

        LaunchedEffect(aiState) {
            if (aiState is AiState.Default && selectedOption == SearchOption.DISEASE)
                textFieldState.clearText()
        }

        HContainer.TitledScreen(modifier = modifier, title = HStrings.ai.uppercase()) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                HTextBar.Items(
                    enabled = searchBarEnabled,
                    state = textFieldState,
                    modifier = Modifier
                        .background(
                            color = HTheme.colors.onBackgroundContainer,
                            shape = HTheme.shapes.rounded12
                        )
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min),
                    lineLimits = TextFieldLineLimits.SingleLine,
                    hintText = hintText,
                    contentModifier = Modifier.padding(horizontal = 15.dp),
                    frontItem = {
                        HIcons.AI(
                            modifier = Modifier
                                .padding(vertical = 15.dp)
                                .size(20.dp),
                            tint = HTheme.colors.secondary
                        )
                    },
                    backItem = {
                        HButton.Default(
                            buttonShape = HTheme.shapes.circular,
                            modifier = Modifier
                                .fillMaxHeight(0.7f)
                                .aspectRatio(1f)
                                .clickable(
                                    interactionSource = null,
                                    indication = null,
                                    onClick = {
                                        if (aiState !is AiState.Loading)
                                            when (selectedOption) {
                                                SearchOption.PROCEDURE ->
                                                    if (textFieldState.text.isNotEmpty())
                                                        onEvent(
                                                            AiEvent.OnSearchProcedure(
                                                                FeatureProcedureRequest(
                                                                    serviceName = textFieldState.text.toString()
                                                                )
                                                            )
                                                        )

                                                SearchOption.DISEASE
                                                    -> if (symptomTags.isNotEmpty())
                                                    onEvent(
                                                        AiEvent.OnSearchDisease(
                                                            FeatureDiseaseRequest(
                                                                symptoms = symptomTags
                                                            )
                                                        )
                                                    )

                                                SearchOption.SUGGESTION ->
                                                    if (textFieldState.text.isNotEmpty())
                                                        onEvent(
                                                            AiEvent.OnSearchSuggestion(
                                                                FeatureSuggestionRequest(input = textFieldState.text.toString())
                                                            )
                                                        )

                                            }
                                    }
                                ),
                            contentPadding = PaddingValues(4.dp),
                            containerColor = HTheme.colors.secondary,
                            contentColor = HTheme.colors.onBackgroundContainer
                        ) { HIcons.CHEVRON(tint = HTheme.colors.onBackgroundContainer) }
                    }
                )

                HOptionSelector.SingleLine(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    options = SearchOption.entries,
                    selectedOption = selectedOption,
                    onOptionSelected = { selectedOption = it },
                    extractName = { stringResource(it.titleText).capitalize() },
                )

                AnimatedVisibility(visible = isSymptomsVisible) {
                    SymptomsTags(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        symptomTags = symptomTags,
                        onAdd = symptomTags::add,
                        onRemove = symptomTags::remove
                    )
                }
            }
            AnimatedVisibility(
                modifier = Modifier.padding(top = 15.dp),
                visible = isInfoShown,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                if (aiState is AiState.Found)
                    (aiState as AiState.Found).info.Display(
                        modifier = Modifier.fillMaxSize(),
                        onTextClick = onTextClick
                    )
            }
            AnimatedVisibility(
                visible = aiState is AiState.Loading,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                LoadingScreen()
            }
        }
    }

    @Composable
    private fun SymptomsTags(
        modifier: Modifier = Modifier,
        symptomTags: List<String>,
        onAdd: (String) -> Unit,
        onRemove: (String) -> Unit
    ) {

        val textFieldState = rememberTextFieldState()

        HContainer.Default(modifier = modifier) {
            if (symptomTags.isNotEmpty()) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FlowRow(
                        modifier = Modifier.animateContentSize(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        symptomTags.forEach { tag ->
                            SymptomTag(
                                title = tag,
                                onRemove = onRemove
                            )
                        }
                    }

                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = HTheme.colors.secondary
                    )
                }
            }

            HTextBar.Items(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                contentModifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                lineLimits = TextFieldLineLimits.SingleLine,
                hintText = HStrings.addSymptom.capitalize(),
                state = textFieldState,
                backItem = {
                    HIcons.PLUS(
                        modifier = Modifier
                            .fillMaxHeight(0.8f)
                            .clickable(
                                interactionSource = null,
                                indication = null,
                                onClick = {
                                    val text = textFieldState.text.toString().trim()
                                    if (text.isNotEmpty()) {
                                        onAdd(text)
                                        textFieldState.clearText()
                                    }
                                }
                            ),
                        tint = HTheme.colors.primary
                    )
                }
            )
        }
    }

    @Composable
    private fun SymptomTag(
        modifier: Modifier = Modifier,
        title: String,
        onRemove: (String) -> Unit
    ) = HContainer.Default(
        modifier = modifier.wrapContentSize(),
        paddingValues = PaddingValues(vertical = 5.dp, horizontal = 10.dp),
        backgroundColor = HTheme.colors.background,
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
        ) {
            HText.SingleLine(text = title)
            HIcons.CROSS(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(0.45f)
                    .clickable(
                        interactionSource = null,
                        indication = null,
                        onClick = { onRemove(title) }
                    )
            )
        }
    }

    @Composable
    override fun LoadingScreen(vararg params: Any) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            HProgressIndicator.Circular(
                containerColor = HTheme.colors.background
            )
        }
    }
}
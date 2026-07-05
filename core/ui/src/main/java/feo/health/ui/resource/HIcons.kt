package feo.health.ui.resource

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import feo.health.ui.R
import feo.health.ui.theme.HTheme

/**
 * Sealed interface representing vector graphics icons used across the application.
 */
sealed interface HIcons {

    /**
     * Renders the vector icon drawable as a Composable.
     *
     * @param modifier The [Modifier] to apply to the icon layout container.
     * @param tint The color tint to paint the icon vector path. Defaults to [HTheme.colors.primary].
     */
    @Composable
    operator fun invoke(modifier: Modifier = Modifier, tint: Color = HTheme.colors.primary)

    /**
     * Artificial intelligence assistance icon.
     */
    data object AI : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.ai)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Upward navigation/arrow icon.
     */
    data object ARROW_UP : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.arrow_up)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Rightward arrow icon.
     */
    data object ARROW_RIGHT : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.arrow_right)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Leftward back-navigation arrow icon.
     */
    data object ARROW_LEFT : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.arrow_left)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Caution/attention alert icon.
     */
    data object ATTENTION : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.attention)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Trash bin delete icon.
     */
    data object BIN : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.bin)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Birthday cake info icon.
     */
    data object BIRTHDAY : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.birthday)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Currency/cash finance icon.
     */
    data object CASH : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.cash)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Chat message dialogue bubble icon.
     */
    data object CHAT : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.chat)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Right-pointing chevron disclosure details icon.
     */
    data object CHEVRON : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.chevron)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Clock/duration stopwatch history icon.
     */
    data object CLOCK : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.clock)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Close/cancel cross indicator icon.
     */
    data object CROSS : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.cross)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Medical practitioner specialist doctor icon.
     */
    data object DOCTOR : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.doctor)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Electronic mail letter envelope icon.
     */
    data object EMAIL : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.email)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Search parameters funnel filter settings icon.
     */
    data object FILTER : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.filter)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Heart favorite selection icon.
     */
    data object HEART : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.heart)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Home catalog search dashboard icon.
     */
    data object HOME : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.home)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Medical hospital clinic building icon.
     */
    data object HOSPITAL : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.hospital)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Information alert circle icon.
     */
    data object INFORMATION_CIRCLE : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.information_circle)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Security lock password input icon.
     */
    data object KEY : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.key)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Geolocation map coordinates pin marker icon.
     */
    data object LOCATION_MARKER : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.location_marker)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Medical capsule pill medicine drug icon.
     */
    data object MEDICINE : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.medicine)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Plus add item action icon.
     */
    data object PLUS : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.plus)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Telephone contact calling icon.
     */
    data object PHONE : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.phone)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Query magnification search bar icon.
     */
    data object SEARCH : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.search)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Favorite star selection rating icon.
     */
    data object STAR : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.star)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Stethoscope medical check diagnosis icon.
     */
    data object STETHOSCOPE : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.stethoscope)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Success status checkmark circle alert icon.
     */
    data object SUCCESS_CIRCLE : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.success)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * User profile account login icon.
     */
    data object USER : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.user)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Global network web search magnifying icon.
     */
    data object WEB_SEARCH : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.web_search)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    /**
     * Body mass index weight scale indicator icon.
     */
    data object WEIGHT : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.weight)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }
}
package feo.health.ui.component

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import feo.health.ui.R
import feo.health.ui.theme.HTheme

sealed interface HIcons {

    @Composable
    operator fun invoke(modifier: Modifier = Modifier, tint: Color = HTheme.colors.primary)

    data object ARROW_UP : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.arrow_up)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    data object ARROW_RIGHT : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.arrow_right)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    data object ARROW_LEFT : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.arrow_left)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    data object CASH : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.cash)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    data object CHAT : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.cash)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    data object CLOCK : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.clock)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    data object FILTER : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.filter)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    data object HEART : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.heart)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    data object HOME : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.home)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    data object INFORMATION_CIRCLE : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.information_circle)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    data object LOCATION_MARKER : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.location_marker)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    data object PHONE : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.phone)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    data object SEARCH : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.search)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }
}
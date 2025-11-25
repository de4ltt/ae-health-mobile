package feo.health.ui.resource

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

    data object AI : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.ai)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

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

    data object ATTENTION : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.attention)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    data object BIN : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.bin)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    data object BIRTHDAY : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.birthday)
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
            val painter: Painter = painterResource(R.drawable.chat)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    data object CHEVRON : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.chevron)
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

    data object CROSS : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.cross)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    data object DOCTOR : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.doctor)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    data object EMAIL : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.email)
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

    data object HOSPITAL : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.hospital)
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

    data object KEY : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.key)
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

    data object MEDICINE : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.medicine)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    data object PLUS : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.plus)
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

    data object STAR : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.star)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    data object STETHOSCOPE : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.stethoscope)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    data object SUCCESS_CIRCLE : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.success)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    data object USER : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.user)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    data object WEB_SEARCH : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.web_search)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }

    data object WEIGHT : HIcons {
        @Composable
        override operator fun invoke(modifier: Modifier, tint: Color) {
            val painter: Painter = painterResource(R.drawable.weight)
            Icon(painter = painter, modifier = modifier, tint = tint, contentDescription = null)
        }
    }
}
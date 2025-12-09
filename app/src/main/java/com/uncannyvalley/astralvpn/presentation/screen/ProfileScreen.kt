package com.uncannyvalley.astralvpn.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uncannyvalley.astralvpn.R
import com.uncannyvalley.astralvpn.presentation.components.PremiumButton
import com.uncannyvalley.astralvpn.presentation.profile.ProfileUiState
import com.uncannyvalley.astralvpn.presentation.theme.AstralVPNTheme

@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    onEditButtonClick: () -> Unit,
    onGetPremiumClick: () -> Unit,
    onBack: () -> Unit
) {
    when (uiState) {
        is ProfileUiState.Loaded -> ProfileLoadedScreen(
            uiState = uiState,
            onEditButtonClick = onEditButtonClick,
            onGetPremiumClick = onGetPremiumClick,
            onBack = onBack
        )

        else -> {}
    }
}

@Composable
private fun ProfileLoadedScreen(
    uiState: ProfileUiState.Loaded,
    onEditButtonClick: () -> Unit,
    onGetPremiumClick: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.background_stars),
                    contentScale = ContentScale.FillHeight
                )
                .padding(padding)
        ) {
            // glass
            Box(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, top = 112.dp, bottom = 86.dp)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
                    .paint(
                        painterResource(id = R.drawable.background_glass),
                        contentScale = ContentScale.FillBounds
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(16.dp))
                        .padding(horizontal = 42.dp, vertical = 48.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // top bar
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.btn_return),
                            contentDescription = "Return",
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = ripple(bounded = false),
                                    onClick = onBack
                                )
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Icon(
                            painter = painterResource(R.drawable.btn_edit),
                            contentDescription = "Edit",
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = ripple(bounded = false),
                                    onClick = onEditButtonClick
                                )
                        )
                    }

                    Spacer(modifier = Modifier.height(76.dp))

                    val profileIcon = if (uiState.isPremium) R.drawable.ic_profile_premium
                    else R.drawable.ic_profile_normal

                    Image(
                        painter = painterResource(profileIcon),
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.height(36.dp))

                    Text(
                        text = uiState.userName,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = if (uiState.isPremium) stringResource(
                            R.string.profile_text_premium
                        ) else stringResource(R.string.profile_text_free_plan),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    if (!uiState.isPremium) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(98.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.background_stars_for_btn),
                                contentDescription = null,
                                modifier = Modifier
                                    .alpha(0.5f)
                                    .size(196.dp)
                                    .align(Alignment.Center),
                                contentScale = ContentScale.Fit
                            )

                            PremiumButton(onClick = onGetPremiumClick)
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ProfileScreenPreview() {
    AstralVPNTheme(darkTheme = true) {
        ProfileScreen(
            onBack = {},
            onEditButtonClick = {},
            onGetPremiumClick = {},
            uiState = ProfileUiState.Loaded(
                userName = "Ivan Ivanov",
                isPremium = false
            ),
        )
    }
}
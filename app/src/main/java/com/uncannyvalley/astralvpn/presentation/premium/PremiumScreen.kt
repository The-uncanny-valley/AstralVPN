package com.uncannyvalley.astralvpn.presentation.premium

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uncannyvalley.astralvpn.R
import com.uncannyvalley.astralvpn.presentation.components.CustomLineButton
import com.uncannyvalley.astralvpn.presentation.components.SubscribeButton
import com.uncannyvalley.astralvpn.presentation.theme.AstralVPNTheme

@Composable
fun PremiumScreen(
    uiState: PremiumUiState,
    onPlanSelected: (PremiumUiState) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) { padding ->


        Column(
            modifier = Modifier.padding(38.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(0.5f))

            Text(
                text = stringResource(id = R.string.premium_title),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(48.dp))

            Text(
                text = stringResource(id = R.string.premium_text),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(48.dp))

            SubscriptionPlan(
                chosen = true,
                text = stringResource(R.string.premium_year_plan),
                onClick = { }
            )

            Spacer(modifier = Modifier.height(12.dp))

            SubscriptionPlan(
                chosen = false,
                text = stringResource(R.string.premium_month_plan),
                onClick = {
                    onPlanSelected(PremiumUiState.MonthPlan)
                }
            )

            Spacer(modifier = Modifier.height(28.dp))

            SubscribeButton(
                text = stringResource(R.string.premium_get_premium_btn),
                onClick = {
                    onPlanSelected(PremiumUiState.YearPlan)
                },
                modifier = Modifier
            )

            Spacer(modifier = Modifier.height(18.dp))

            CustomLineButton(
                text = stringResource(R.string.premium_enter_promo_code),
                onClick = {}
            )

            CustomLineButton(
                text = stringResource(R.string.premium_terms_and_conditions),
                onClick = {}
            )

            Spacer(modifier = Modifier.weight(0.5f))
        }
    }
}

@Preview(
    name = "Premium Screen - Month",
    showBackground = true
)
@Composable
fun PremiumPreview() {
    AstralVPNTheme(darkTheme = true) {
        PremiumScreen(
            uiState = PremiumUiState.MonthPlan,
            onPlanSelected = { }
        )
    }
}

@Composable
fun SubscriptionPlan(
    text: String,
    chosen: Boolean,
    onClick: () -> Unit
) {
    val tint = if (chosen) MaterialTheme.colorScheme.onBackground
    else MaterialTheme.colorScheme.onSurfaceVariant

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                color = if (chosen) MaterialTheme.colorScheme.onBackground else Color.Transparent,
                width = 2.dp,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                color = tint,
                style = MaterialTheme.typography.labelLarge
            )

            Icon(
                painter = painterResource(
                    if (chosen) R.drawable.ic_chosen_plan
                    else R.drawable.ic_not_chosen_plan
                ),
                tint = tint,
                contentDescription = stringResource(
                    if (chosen) R.string.premium_ic_chosen
                    else R.string.premium_ic_not_chosen
                )
            )
        }
    }
}
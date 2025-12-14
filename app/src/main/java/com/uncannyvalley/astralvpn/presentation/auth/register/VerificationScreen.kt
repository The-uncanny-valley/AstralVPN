package com.uncannyvalley.astralvpn.presentation.auth.register

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uncannyvalley.astralvpn.R
import com.uncannyvalley.astralvpn.presentation.components.AuthButton
import com.uncannyvalley.astralvpn.presentation.theme.AstralVPNTheme
import kotlinx.coroutines.delay

@Composable
fun VerificationScreen(
    uiState: VerificationUiState,
    email: String,
    onCodeChange: (String) -> Unit,
    onVerifyClick: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 34.dp, vertical = 92.dp)
                .padding(padding),
            horizontalAlignment = Alignment.Start
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

            Spacer(modifier = Modifier.height(42.dp))

            Text(
                text = stringResource(R.string.verification_title),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = stringResource(R.string.verification_text) + " $email",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Code input field
            VerificationCodeField(
                code = uiState.code,
                onCodeChange = onCodeChange,
                error = uiState.errorMessage != null, // ?
                modifier = Modifier.fillMaxWidth(),
                uiState = uiState
            )

            AuthButton(
                text = stringResource(R.string.verification_verify_btn),
                enabled = uiState.code.length == 4 && !uiState.isLoading,
                onClick = { onVerifyClick() }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.verification_resend_code),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun VerificationCodeField(
    code: String,
    onCodeChange: (String) -> Unit,
    error: Boolean?,
    modifier: Modifier = Modifier,
    uiState: VerificationUiState
) {
    val focusRequester = remember { FocusRequester() }

    var isFocused by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(modifier = modifier) {
        // Limit to 6 digits and only allow numbers
        BasicTextField(
            value = code,
            onValueChange = { newValue ->
                // Only allow digits and limit to 6 characters
                val filtered = newValue.filter { it.isDigit() }.take(4)
                onCodeChange(filtered)
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusable()
                .focusRequester(focusRequester)
                .onFocusChanged { isFocused = it.isFocused }
            ,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            decorationBox = { innerTextField ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Code digits display
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        for (i in 0 until 4) {
                            val digit = if (i < code.length) code[i].toString() else ""
                            val showCursor =
                                isFocused && i == code.length
                            val isActiveBox = isFocused && i == code.length

                            val isError = error == true

                            val borderColor = when {
                                isError -> MaterialTheme.colorScheme.error
                                isActiveBox -> MaterialTheme.colorScheme.outlineVariant
                                else -> MaterialTheme.colorScheme.outline
                            }

                            val borderWidth = when {
                                isError -> 1.dp
                                isActiveBox -> 2.dp
                                else -> 1.dp
                            }

                            val backgroundColor = if (isError) {
                                MaterialTheme.colorScheme.errorContainer
                            } else {
                                MaterialTheme.colorScheme.primaryContainer
                            }

                            val textColor = if (isError) {
                                MaterialTheme.colorScheme.onErrorContainer
                            } else {
                                MaterialTheme.colorScheme.onPrimaryContainer
                            }

                            Box(
                                modifier = Modifier
                                    .size(
                                        width = 68.dp,
                                        height = 50.dp
                                    )
                                    .border(
                                        shape = RoundedCornerShape(8.dp),
                                        color = borderColor,
                                        width = borderWidth
                                    )
                                    .background(
                                        color = backgroundColor,
                                        shape = RoundedCornerShape(8.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                if (digit.isNotEmpty()) {
                                    Text(
                                        text = digit,
                                        style = MaterialTheme.typography.headlineMedium,
                                        color = textColor
                                    )
                                } else if (showCursor) {
                                    BlinkingCursor()
                                }
                            }
                        }
                    }

                    // Hidden text field for input
                    Box(
                        modifier = Modifier
                            .height(0.dp)
                            .alpha(0f)
                    ) {
                        innerTextField()
                    }
                }
            }
        )

        // Error message
        if (!uiState.errorMessage.isNullOrEmpty()) {
            Text(
                text = stringResource(R.string.verification_wrong_code),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
        } else {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun BlinkingCursor(modifier: Modifier = Modifier) {
    var visible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(500)
            visible = !visible
        }
    }

    if (visible) {
        Box(
            modifier = Modifier
                .width(3.dp)
                .height(28.dp)
                .background(MaterialTheme.colorScheme.onPrimaryContainer)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun VerificationScreenPreview() {
    val previewVM = FakeVerificationViewModel()

    AstralVPNTheme(darkTheme = true) {
        VerificationScreen(
            uiState = previewVM.uiState.collectAsState().value,
            onCodeChange = {},
            onVerifyClick = {},
            onBack = {},
            email = "yourname@gmail.com"
        )
    }
}
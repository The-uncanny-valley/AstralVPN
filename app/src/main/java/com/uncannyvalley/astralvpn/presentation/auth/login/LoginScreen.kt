package com.uncannyvalley.astralvpn.presentation.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uncannyvalley.astralvpn.R
import com.uncannyvalley.astralvpn.presentation.components.AuthButton
import com.uncannyvalley.astralvpn.presentation.components.CustomLineButton
import com.uncannyvalley.astralvpn.presentation.theme.AstralVPNTheme
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun LoginScreen(
    viewModel: LoginViewModelInterface,
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit,
    onContinueWithoutRegistration: () -> Unit,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(viewModel.events) {
        viewModel.events.collect { event ->
            when (event) {
                LoginEvent.Success -> onLoginSuccess()
//                is LoginEvent.Error -> {
//                    // show snackbar or toast later
//                }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .background(MaterialTheme.colorScheme.background)
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .imePadding()
                .padding(
                    start = 46.dp,
                    end = 42.dp,
                    top = 120.dp
                )
                .padding(padding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(R.string.login_title),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = uiState.email,
                onValueChange = { viewModel.onEmailChanged(it) },
                label = {
                    Text(
                        text = stringResource(R.string.login_email_label),
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                singleLine = true,

                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_email),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }
            )

            if (uiState.email.isNotBlank() && !uiState.isEmailValid) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.login_email_error),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            } else {
                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(6.dp))

            OutlinedTextField(
                value = uiState.password,
                onValueChange = { viewModel.onPasswordChanged(it) },
                label = {
                    Text(
                        text = stringResource(R.string.register_password_label),
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),

                visualTransformation = if (passwordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                    autoCorrectEnabled = false
                ),
                singleLine = true,

                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_custom_password),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                },

                trailingIcon = {
                    IconButton(
                        onClick = { passwordVisible = !passwordVisible }
                    ) {
                        Icon(
                            painter = painterResource(
                                if (passwordVisible) R.drawable.ic_visibility_on
                                else R.drawable.ic_visibility_off
                            ),
                            contentDescription = if (passwordVisible) "Hide password"
                            else "Show password",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(end = 12.dp)
                        )
                    }
                }
            )

            uiState.errorMessage?.let { error ->
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            AuthButton(
                text = stringResource(R.string.login_btn),
                enabled = uiState.isSubmitEnabled,
                onClick = { viewModel.onLoginClick() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomLineButton(
                text = stringResource(R.string.login_forgot_password),
                onClick = { /* TODO: add click listener */ }
            )

            CustomLineButton(
                text = stringResource(R.string.login_register),
                onClick = onRegisterClick
            )

            CustomLineButton(
                text = stringResource(R.string.login_skip),
                onClick = onContinueWithoutRegistration
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    AstralVPNTheme(darkTheme = true) {
        LoginScreen(
            onBack = {},
            onLoginSuccess = {},
            viewModel = FakeLoginViewModel(),
            onContinueWithoutRegistration = {},
            onRegisterClick = {}
        )
    }
}

class FakeLoginViewModel : LoginViewModelInterface {
    private val _uiState = MutableStateFlow(
        LoginUiState(
            email = "",
            password = ""
        )
    )
    override val uiState: StateFlow<LoginUiState> = _uiState
    override val events = MutableSharedFlow<LoginEvent>()

    override fun onEmailChanged(value: String) {}
    override fun onPasswordChanged(value: String) {}
    override fun onLoginClick() {}
}

interface LoginViewModelInterface {
    val uiState: StateFlow<LoginUiState>
    val events: SharedFlow<LoginEvent>

    fun onEmailChanged(value: String)
    fun onPasswordChanged(value: String)
    fun onLoginClick()
}
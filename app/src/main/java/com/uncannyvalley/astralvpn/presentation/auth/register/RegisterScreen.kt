package com.uncannyvalley.astralvpn.presentation.auth.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Checkbox
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
fun RegisterScreen(
    viewModel: RegisterViewModelInterface,
    onEmailSend: () -> Unit,
    onLoginClick: () -> Unit,
    onContinueWithoutRegistration: () -> Unit,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(viewModel.events) {
        viewModel.events.collect { event ->
            when (event) {
                RegisterEvent.VerificationEmailSent -> onEmailSend()
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
                text = stringResource(R.string.register_title),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = uiState.name,
                onValueChange = { viewModel.onNameChanged(it) },
                label = {
                    Text(
                        text = stringResource(R.string.register_name_label),
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),

                keyboardOptions = KeyboardOptions(
                    autoCorrectEnabled = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                visualTransformation = VisualTransformation.None,
                singleLine = true,

                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_user),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }
            )

            Spacer(modifier = Modifier.height(18.dp))

            OutlinedTextField(
                value = uiState.email,
                onValueChange = { viewModel.onEmailChanged(it) },
                label = {
                    Text(
                        text = stringResource(R.string.register_email_label),
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
                    text = stringResource(R.string.register_email_error),
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
                        painter = painterResource(R.drawable.ic_password),
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
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            TermsAgreementRow(
                checked = uiState.termsAccepted,
                onCheckedChange = { checked ->
                    viewModel.onTermsChecked(checked)
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            AuthButton(
                text = stringResource(R.string.register_btn),
                enabled = uiState.isSubmitEnabled,
                onClick = { viewModel.onRegisterClick() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomLineButton(
                text = stringResource(R.string.register_log_in),
                onClick = onLoginClick
            )

            CustomLineButton(
                text = stringResource(R.string.register_skip),
                onClick = onContinueWithoutRegistration
            )
        }
    }
}

@Composable
fun TermsAgreementRow(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onCheckedChange(!checked) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )

        Text(
            text = stringResource(R.string.register_accept_terms),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    val fakeState = RegisterUiState(
        email = "",
        name = "",
        password = "",
        termsAccepted = false
    )
    AstralVPNTheme(darkTheme = true) {
        RegisterScreen(
            onBack = {},
            onEmailSend = {},
            viewModel = FakeRegisterViewModel(),
            onContinueWithoutRegistration = {},
            onLoginClick = {}
        )
    }
}

class FakeRegisterViewModel : RegisterViewModelInterface {
    private val _uiState = MutableStateFlow(
        RegisterUiState(
            email = "",
            name = "",
            password = "",
            termsAccepted = false
        )
    )
    override val uiState: StateFlow<RegisterUiState> = _uiState
    override val events = MutableSharedFlow<RegisterEvent>()

    override fun onEmailChanged(value: String) {}
    override fun onNameChanged(value: String) {}
    override fun onPasswordChanged(value: String) {}
    override fun onTermsChecked(value: Boolean) {}
    override fun onRegisterClick() {}
}

interface RegisterViewModelInterface {
    val uiState: StateFlow<RegisterUiState>
    val events: SharedFlow<RegisterEvent>

    fun onEmailChanged(value: String)
    fun onNameChanged(value: String)
    fun onPasswordChanged(value: String)
    fun onTermsChecked(value: Boolean)
    fun onRegisterClick()
}
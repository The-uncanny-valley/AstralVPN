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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    onRegisterSuccess: () -> Unit,
    onContinueWithoutRegistration: () -> Unit,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel.events) {
        viewModel.events.collect { event ->
            when (event) {
                is RegisterEvent.CodeSent ->
                    onRegisterSuccess()

                RegisterEvent.Success -> { /* unused for now */ }
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

            key(uiState.email.isNotBlank()) {
                OutlinedTextField(
                    value = uiState.email,
                    onValueChange = { viewModel.onEmailChanged(it) },
                    label = { Text(stringResource(R.string.register_email_label)) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true
                )
            }

            if (uiState.email.isNotBlank() && !uiState.isEmailValid) {
                Text(
                    text = "Enter a valid email address",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            OutlinedTextField(
                value = uiState.name,
                onValueChange = { viewModel.onNameChanged(it) },
                label = { Text(stringResource(R.string.register_name_label)) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(18.dp))

            OutlinedTextField(
                value = uiState.password,
                onValueChange = { viewModel.onPasswordChanged(it) },
                label = { Text(stringResource(R.string.register_password_label)) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),

                visualTransformation = PasswordVisualTransformation(),
                
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            TermsAgreementRow(
                checked = uiState.termsAccepted,
                onCheckedChange = { checked ->
                    viewModel.onTermsChecked(checked) }
            )

            Spacer(modifier = Modifier.height(8.dp))

            AuthButton(
                text = "Sign up",
                enabled = uiState.isSubmitEnabled,
                onClick = { viewModel.onRegisterClick() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomLineButton(
                text = stringResource(R.string.register_log_in),
                onClick = { /*TODO: implement login navigation later*/ }
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
            color = MaterialTheme.colorScheme.onBackground
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
            onRegisterSuccess = {},
            viewModel = FakeRegisterViewModel(),
            onContinueWithoutRegistration = {}
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
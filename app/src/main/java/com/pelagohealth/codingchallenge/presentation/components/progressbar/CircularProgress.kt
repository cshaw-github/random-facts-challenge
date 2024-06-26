package com.pelagohealth.codingchallenge.presentation.components.progressbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.pelagohealth.codingchallenge.presentation.components.dimens.Dimens
import com.pelagohealth.codingchallenge.ui.theme.PelagoCodingChallengeTheme

@Composable
fun CircularProgress(
	modifier: Modifier = Modifier,
	message: String,
) {
	Column(
		modifier = modifier
			.fillMaxSize(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		CircularProgressIndicator(
			modifier = Modifier.padding(Dimens.medium),
			strokeWidth = Dimens.xSmall,
		)
		Text(modifier = Modifier.padding(Dimens.medium), text = message)
	}
}

@Preview(showBackground = true)
@PreviewLightDark
@Composable
private fun CircularProgressPreview() {
	PelagoCodingChallengeTheme {
		Surface {
			CircularProgress(message = "Loading")
		}
	}
}

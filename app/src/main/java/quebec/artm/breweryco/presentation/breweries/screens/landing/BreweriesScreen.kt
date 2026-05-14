package quebec.artm.breweryco.presentation.breweries.screens.landing

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import quebec.artm.breweryco.presentation.breweries.screens.landing.models.BreweryUiData
import quebec.artm.breweryco.ui.theme.GreenLoading
import quebec.artm.breweryco.ui.theme.RedErrors

@Composable
fun BreweriesScreen(vm: BreweriesScreenViewModel) {
    val state by vm.state.collectAsState()
    BreweriesRender(
        modifier = Modifier.fillMaxWidth(),
        breweries = state.breweries,
        isLoading = state.isLoading,
        error = state.errorMessage
    )
}

@Composable
private fun BreweriesRender(
    modifier: Modifier = Modifier,
    breweries: List<BreweryUiData>,
    isLoading: Boolean,
    error: String?
) {

    Box(modifier = modifier.fillMaxSize()) {

        when {

            // Loading -> Circular Progress Indicator
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = GreenLoading
                )
            }

            // Error -> Red Text
            error != null -> {
                Text(
                    text = error,
                    color = RedErrors,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                )
            }

            // Success -> Breweries list
            else -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {

                    itemsIndexed(
                        items = breweries,
                        key = { _, item -> item.key }
                    ) { index, brewery ->

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 8.dp),
                            text = brewery.name
                        )

                        if (index < breweries.size - 1) {
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
    }
}
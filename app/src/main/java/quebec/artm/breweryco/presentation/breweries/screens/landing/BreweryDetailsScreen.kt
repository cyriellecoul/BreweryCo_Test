package quebec.artm.breweryco.presentation.breweries.screens.landing

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import quebec.artm.breweryco.presentation.common.components.InfoRow

@Composable
fun BreweryDetailsScreen(
    breweryId: String,
    viewModel: BreweryViewModel = hiltViewModel()
) {
    val brewery = viewModel.getBreweryById(breweryId)

    if (brewery == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Brewery not found")
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = brewery.name,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        InfoRow(label = "Phone", value = brewery.phone)
        InfoRow(label = "Address", value = brewery.address1)
        InfoRow(label = "Country", value = brewery.country)

    }
}
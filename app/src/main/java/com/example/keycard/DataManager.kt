package com.example.keycard.ui.theme

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


// Requires Context to write to internal storage of Android
// and dataStore is the field name.
// "preferences" does not only mean stuff in UI settings, it's just shorthand for key-value storage.
private val Context.dataStore by preferencesDataStore(name = "keycard_data")

/**
 * Class for storing persistent data for the whole app
 */
class DataManager(private val context: Context) {

    val NUM_CARDS_ON_UNLOCK = longPreferencesKey("num_cards_on_unlock")
    val NUM_CARDS_DONE_TODAY = longPreferencesKey("cards_done_today")
    val NUM_CARDS_DONE_TOTAL = longPreferencesKey("cards_done_total")
    val NUM_UNLOCKS_DONE_TODAY = longPreferencesKey("unlocks_done_today")
    val NUM_UNLOCKS_DONE_TOTAL = longPreferencesKey("unlocks_done_total")

    // Flows that get updated whenever values on disk change
    // .data is the actual property of type Flow<Preferences>

    data class UserPreferences(
        private val numCardsOnUnlock: Long
    )

    data class UserStatistics(
        private val numCardsDoneToday: Long,
        private val numCardsDoneTotal: Long,
        private val numUnlocksDoneToday: Long,
        private val numUnlocksDoneTotal: Long
    )

    val userPreferences: Flow<UserPreferences> = context.dataStore.data.map { data ->
        UserPreferences(
            numCardsOnUnlock = data[NUM_CARDS_ON_UNLOCK] ?: 1L,
            )
    }

    val userStatistics: Flow<UserStatistics> = context.dataStore.data.map { data ->
        UserStatistics(
            numCardsDoneToday = data[NUM_CARDS_DONE_TODAY] ?: 0L,
            numCardsDoneTotal = data[NUM_CARDS_DONE_TOTAL] ?: 0L,
            numUnlocksDoneToday = data[NUM_UNLOCKS_DONE_TODAY] ?: 0L,
            numUnlocksDoneTotal = data[NUM_UNLOCKS_DONE_TOTAL] ?: 0L
        )
    }

    // .edit is a suspend function, so must make this function suspend and wrap inside coroutine.
    suspend fun savePreferencesToDisk(
        newNumCardsOnUnlock: Long,
    ) {
        context.dataStore.edit { data ->
            data[NUM_CARDS_ON_UNLOCK] = newNumCardsOnUnlock
        }
    }

    suspend fun saveStatisticsToDisk(
        newNumCardsDoneToday: Long,
        newNumCardsDoneTotal: Long,
        newNumUnlocksDoneToday: Long,
        newNumUnlocksDoneTotal: Long
    ) {
        context.dataStore.edit { data ->
            data[NUM_CARDS_DONE_TODAY] = newNumCardsDoneToday
            data[NUM_CARDS_DONE_TOTAL] = newNumCardsDoneTotal
            data[NUM_UNLOCKS_DONE_TODAY] = newNumUnlocksDoneToday
            data[NUM_UNLOCKS_DONE_TOTAL] = newNumUnlocksDoneTotal
        }

    }


}
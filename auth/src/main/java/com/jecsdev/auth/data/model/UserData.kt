package com.jecsdev.auth.data.model

import com.jecsdev.auth.domain.entities.User

/**
 * This class represents the data from the user.
 * @param userId user identifier.
 * @param userName user name from account.
 * @param profilePictureUri  uri to show profile picture.
 */
data class UserData(
    val userId: String,
    val userName: String,
    val profilePictureUri: String?
) {
    fun toDomain(): User = User(
        userId = userId,
        userName = userName,
        profilePictureUri = profilePictureUri
    )
}

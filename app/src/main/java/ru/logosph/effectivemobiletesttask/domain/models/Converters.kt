package ru.logosph.effectivemobiletesttask.domain.models

fun FavoriteEntity.toVacancy(): Vacancy {
    return Vacancy(
        id = this.id,
        lookingNumber = this.lookingNumber,
        title = this.title,
        address = this.address,
        company = this.company,
        experience = this.experience,
        publishedDate = this.publishedDate,
        salary = this.salary,
        schedules = this.schedules,
        appliedNumber = this.appliedNumber,
        description = this.description,
        responsibilities = this.responsibilities,
        questions = this.questions
    )
}

fun Vacancy.toFavoriteEntity(): FavoriteEntity {
    return FavoriteEntity(
        id = this.id!!,
        lookingNumber = this.lookingNumber,
        title = this.title,
        address = this.address,
        company = this.company,
        experience = this.experience,
        publishedDate = this.publishedDate,
        salary = this.salary,
        schedules = this.schedules,
        appliedNumber = this.appliedNumber,
        description = this.description,
        responsibilities = this.responsibilities,
        questions = this.questions
    )
}
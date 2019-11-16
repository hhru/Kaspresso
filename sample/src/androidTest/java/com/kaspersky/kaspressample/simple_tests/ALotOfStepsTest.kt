package com.kaspersky.kaspressample.simple_tests

import android.Manifest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ALotOfStepsTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun test() {
        before {
            activityTestRule.launchActivity(null)
        }.after {
        }.run {
            step("Авторизуем пользователя скрытно [u972585kzridgy@example.com]") {}
            step("Открываем экран Поиска") {}
            step("Ищем вакансию по названию должности [Вакансия в автопоиске 971392YLFXHMJ]") {}
            step("Меняем тип сортировки вакансий на 'по возрастанию зарплаты'") {}
            step("Создаем автопоиск") {}
            step("Открываем вкладку автопоисков") {}
            step("Создаем новую вакансию для проверки автопоиска") {
                step("Хардкорное ожидание, чтобы запрос на просмотренные вакансии тоже уходил") {}
            }
            step("Открываем автопоиск на позиции 0") {}
            step("Проверяем, что отобразился заголовок 'Просмотренные вакансии' на позиции 2") {}
            step("Проверяем элементы списка вакансий на позиции 3") {
                step("Проверяем, что название компании совпадает с '973278PRFSZYG'") {}
            }
            step("Проверяем элементы списка вакансий на позиции 4") {
                step("Проверяем, что название компании совпадает с '974439FHINHLW'") {}
            }
            step("Проверяем, что отобразился заголовок 'Новые вакансии'") {}
            step("Проверяем элементы списка вакансий на позиции 1") {
                step("Проверяем, что название компании совпадает с '000892HWOLONW'") {}
            }
            step("Проверяем элементы списка вакансий на позиции 2") {
                step("Проверяем, что название компании совпадает с '973278PRFSZYG'") {}
            }
            step("Проверяем элементы списка вакансий на позиции 3") {
                step("Проверяем, что название компании совпадает с '974439FHINHLW'") {}

            }
        }
    }

}
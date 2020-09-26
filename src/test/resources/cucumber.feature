#language: ru
Функционал: Ипотека
  @sberbank
  Сценарий: Работа с cucumber на примере Ипотеки сбербанка
    * Выбираем меню Ипотека
    * Выбираем подменю Ипотека на готовое жильё
    * Проверяем переход на страницу
    * Нажимаем кнопку Подать заявку
    * Проверяем заголовок Выберите программу и рассчитайте условия
    * Проверяем цель кредита
    * Проверяем и устанавливаем переключатели
      |Зарплатная карта      |false  |
      |ЗП сертификат         |true   |
      |Материнский капитал   |false  |
      |Скидка домклик        |true   |
      |Страхование жизни     |false  |
      |Молодая семья         |true   |
      |Онлайн сделка         |true   |
    * Заполняем поля
      |Стоимость недвижимости|5180000|
      |Первоначальный взнос  |3058000|
      |Количество лет        |30     |
    * Проверяем поля
      |ЕП                    |29082  |Сумма ЕП не совпадает                 |
      |Сумма кредита         |3680000|Сумма кредита не совпадает            |
      |Уровень дохода        |37438  |Сумма необходимого дохода не совпадает|
      |Процентная ставка     |11%    |Так и задумано                        |

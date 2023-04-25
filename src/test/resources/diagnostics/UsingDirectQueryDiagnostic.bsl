
Функция ВыполнитьЗапрос(ТекстЗапроса)

	ДанныеИзAccess = Новый ТаблицаЗначений;

	СтрокаПодключения = "Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=\\server00\База\Данные Товародвижение.mdb";

	Connection = Новый COMОбъект("ADODB.Connection");  // Microsoft Access
	Connection.ConnectionString = СтрокаПодключения;
	Connection.Open();

	RS = Новый COMОбъект("ADODB.Recordset"); // Ошибка: Создание ADODB объекта

	Попытка
		RS.Open(ТекстЗапроса, Connection);
		Для iCount = 0 По RS.Fields.Count-1 Цикл
			ИмяПоля = RS.Fields.Item(iCount).Name;
			ДанныеИзAccess.Колонки.Добавить(ИмяПоля);
		КонецЦикла;
		Пока RS.EOF() = 0 Цикл
			НоваяСтрокаТаблицы = ДанныеИзAccess.Добавить();
			Для Каждого ТекущееПоле Из ДанныеИзAccess.Колонки Цикл
				НоваяСтрокаТаблицы[ТекущееПоле.Имя] = RS.Fields(ТекущееПоле.Имя).Value;
			КонецЦикла;
			RS.MoveNext();
		КонецЦикла;
		RS.Close();
		Connection.Close();
		Возврат ДанныеИзAccess;
	Исключение
		Connection.Close();
		Сообщить(ОписаниеОшибки(),СтатусСообщения.Внимание);
		Сообщить(ТекстЗапроса,СтатусСообщения.Информация);
	КонецПопытки;

КонецФункции


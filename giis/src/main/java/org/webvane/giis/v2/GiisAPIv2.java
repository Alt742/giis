package org.webvane.giis.v2;

import org.webvane.framework.mvc.ResultFW;
import org.webvane.giis.v2.model.*;

import java.util.List;
import java.util.Map;

public interface GiisAPIv2 {

    ResultFW registerRequest ( String requestType, Map<String, Object> args );
    CheckResult checkRequest (Long id );

    OrgInfo checkOrg (Long id );

    ResultFW setPropucts ( List<String> uins );                         // принимаем юины для обработки шаг 1
    ResultFW delProduct ( Long id );
    CheckUinsResult getPropuctUinStatus (Long id );                     // возрощает текущий статус PROCESSING, PREPARED, ERROR, включая статусы по каждому юину
    CheckPropuctStatus getPropuctStatus (Long id );                     // возрощает текущий статус PROCESSING, PREPARED, ERROR,
    CheckPropuctResult getPropuctMergedInfo (Long id );                 // возрощает краткую информацию по каждаму юину, и полную по товару который выброн валидными

    ResultFW finishPropuct(Long id );                             // завершить формирование информции о продукте, и мержит их

    ResultFW deletePropuctUin (Long id, String uin );                 // удалить партию из списка - редоктирование списка
    ResultFW addPropuctUin (Long id, String uin );                    // добавить партию в спиок - редоктирование списка


    // заполняет поле "список УИН"  (минимум одно значение, максимум - ?; вручную либо импорт из .csv или .xlsx файла)
    // тут типизированный запрос возвращает айди который привязывается к товару на фронте

    // Продавец может ожидать получения ответа из ДМДК в карточке товара, либо выполнять какие-либо другие действия в ЛК.
    // тут такой же типизированный запрос который возвращает
    // В списке товаров вновь созданный товар отображается со статусом «Новый + ожидает ответа из ДМДК»
    // этот метод надо типизировать имхо для товара? хз пример ответа и список статусов

    // 3.       продавец может продолжить заполнение оставшихся данных о товаре
    // как узнать информацию о товаре?
    // лучше сделать 2 метда checkStatus и getPropuctInfo - типизированные

}

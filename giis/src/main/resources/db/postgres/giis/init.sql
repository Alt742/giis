-- Бренды
create table if not exists INT_BRANDS
(
    id          bigint,                             -- id бренда
    name   varchar(256),                            -- Название бренда
    update_time bigint
);

-- Материал
create table if not exists INT_DMDK_TYPE_MATERIAL
(
    id          bigint,
    dmdk_type   varchar(256),                       -- тип в ДМДК [JDM_GOLD DM_SILVER DM_PLATINUM DM_PALLADIUM DM_IRIDIUM DM_RHODIUM DM_OSMIUM DM_RUTHENIUM]
    ump_type varchar(256)                           -- тип ЮМП [Золото Серебро Платина Керамика Латунь Ювелирная сталь]
);

-- Тип ювелирного изделия
create table if not exists INT_DMDK_TYPE_OF_JEWELRY
(
    id          bigint,
    dmdk_type   varchar(256),                       -- тип в ДМДК [JT_CHAIN JT_BRACELET JT_RING JT_EARRINGS JT_SUSPENSION JT_WATCH JT_DISHES JS_OTHER]
    ump_type varchar(256)                           -- тип ЮМП [Цепи Браслеты Кольца Серьги Подвески, Брошь Колье]
);

-- Виды ДК
create table if not exists INT_DMDK_TYPE_OF_GEMSTONES
(
    id          bigint,
    dmdk_type   varchar(256),                       -- тип в ДМДК
    ump_type varchar(256)                           -- тип ЮМП
);

-- Размер(каждое изделия имеет свои размеры придётся писать отдельные обработчики для перевода)
create table if not exists INT_DMDK_TYPE_OF_SIZE
(
    id          bigint,
    dmdk_type   varchar(256),                       -- тип в ДМДК
    processing_type varchar(256)                    -- тип процессинка для данного типа размера
);

-- Вставки
create table if not exists INT_INSERTS
(
    id          bigint,
    id_goods    bigint,                             -- ид тавара из таблицы INT_PRODUCT (много вставок один продукт)
    inserts_type   varchar(256),                    -- тип вставки
    update_time bigint                              -- Время обновления записи
);

create table if not exists INT_PRODUCT
(
    id           bigint,
    name         varchar(256),              -- Названия товара
    material     varchar(256),              -- материал ДМДК
    description  varchar(1024),             -- Описания из дмдк
    hallmark     int,                       -- Проба заявленная
    weight       decimal,                   -- Вес
    weight_inserts decimal,                 -- Вес вставок
    price          decimal,                 -- цена
    size           varchar(256),            -- Размер в формате ДМДК
    brandId        bigint,                  -- ИД бренда
    article        varchar(128),            -- артикул
    additionally   varchar(1024),           --  	Допалнительные сведенья
    producer       varchar(256),            -- Производитель
    uin_inp        varchar(128),            -- ЮИН
    okpd2          varchar(128),            -- Код по классификатору ОКПД2
    tnved          varchar(128),            --  	Код по классификатору ТН ВЭД
    owner          varchar(1024),           -- собственник партии
    update_time    bigint
);

create table if not exists INT_REQUEST_STATUS
(
    id          bigint,
    request_type   varchar(128),     -- Тип запроса
    args           varchar(512),     -- аргументы запроса
    status         varchar(128),     -- статус запроса
    message_id     varchar(128),     -- ID запроса
    action_id       bigint,            -- статус запроса
    last_dmdk_check bigint,          -- время последний проверки, в дмдк есть ли результат
    update_time     bigint           --  	время последнего обновления записи
);

create table if not exists INT_PRODUCT_PROCES
(
    id              bigint,
    action_id       bigint,
    status          varchar(128),     -- статус запроса
    response        text,
    update_time     bigint           --  	время последнего обновления записи
);

create table if not exists INT_UIN_STATUS
(
    id              bigint,
    action_id       bigint,
    valid           varchar(128),
    name            varchar(128),
    uin             varchar(128),
    message_id      varchar(128)
);

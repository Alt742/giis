
create table if not exists INT_ACTION
(
    id          bigint,
    system_id   varchar(128),
    user_id     bigint,
    process_id  bigint,
    message_id  varchar(128),
    create_time bigint,
    update_time bigint,
    type        varchar(128),
    status      varchar(128), -- NEW, SENT, SENT_RECEIVED, SEND_ERROR, RECEIVE_RECEIVED
    response    text,
    error       text
);

create table if not exists INT_CONTRACTOR
(
    id              bigint,
    id_int_action   bigint,
    data            text,
    inn             varchar(16),
    update_time     bigint,
    user_id         bigint
);

create table if not exists INT_BATCH_BRIEF
(
    id              bigint,
    id_int_action   bigint,
    data            text,
    uin             varchar(32),
    update_time     bigint,
    user_id         bigint
);

-- бренды складываем в словать с типом PRODUC_BRAND а в продукт MP_PRODUCT складываем в поле  vendor varchar(512), -- Поставщик

create table if not exists INT_DMDK_TRANSFORM
(
    id          bigint,
    param_name  varchar(256),        -- имя трансформируемого параметра: PRODUCT_MATERIAL, PRODUCT_JEWELRY, PRODUCT_GEMSTONES, PRODUCT_SIZE
    dmdk_type   varchar(256),        -- тип в ДМДК [JDM_GOLD DM_SILVER DM_PLATINUM DM_PALLADIUM DM_IRIDIUM DM_RHODIUM DM_OSMIUM DM_RUTHENIUM]
    ump_type varchar(256),           -- тип ЮМП [Золото Серебро Платина Керамика Латунь Ювелирная сталь]
    processing_type varchar(256)     -- тип процессинка для данного типа размера
);

-- Вставки
create table if not exists INT_INSERTS
(
    id          bigint,
    id_goods    bigint,              -- ид тавара из таблицы INT_PRODUCT (много вставок один продукт)
    inserts_type   varchar(256),     -- тип вставки
    update_time bigint               -- Время обновления записи
);
-- я так понимаю, эти атрибуты кладутся в MP_PRODUCT_ATTR и в соответствующие словари и DIC_ITEM

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
    id              bigint,
    request_type    varchar(128),     -- Тип запроса
    args            varchar(512),     -- аргументы запроса
    status          varchar(128),     -- статус запроса
    message_id      varchar(128),     -- статус запроса
    action_id       bigint,            -- статус запроса
    response        text,
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
    message_id      varchar(128),
    variant         bigint,
    difference      text
);
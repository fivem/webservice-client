create table type_element(
id varchar(40) primary key,
parent_id varchar(40),
namespace_name varchar(400) not null,
element_name varchar(400) not null,
element_full_name varchar(400) not null
)

create table element_attr(
id varchar(40) primary key,
element_id varchar(40),
attr_name varchar(400),
attr_value varchar(400),
namespace_name varchar(400)

)

create table namespace_map(
id varchar(40) primary key,
prefix varchar(400),
namespace_name varchar(400)
)
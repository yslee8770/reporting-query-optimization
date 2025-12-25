create table members (
                         id bigint auto_increment primary key,
                         created_at timestamp not null,
                         created_date date not null,
                         created_month int not null,
                         status varchar(20) not null
);

create index idx_member_created_month_status on members (created_month, status);
create index idx_member_status_created_at on members (status, created_at);

create table orders (
                        id bigint auto_increment primary key,
                        member_id bigint not null,
                        ordered_at timestamp not null,
                        ordered_date date not null,
                        status varchar(20) not null,
                        constraint fk_orders_member foreign key (member_id) references members(id)
);

create index idx_order_member_ordered_at on orders (member_id, ordered_at);
create index idx_order_ordered_date_status on orders (ordered_date, status);

create table products (
                          id bigint auto_increment primary key,
                          name varchar(80) not null,
                          status varchar(20) not null
);

create index idx_product_status on products (status);

create table order_items (
                             id bigint auto_increment primary key,
                             order_id bigint not null,
                             product_id bigint not null,
                             quantity int not null,
                             unit_price bigint not null,
                             ordered_date date not null,
                             constraint fk_order_items_order foreign key (order_id) references orders(id),
                             constraint fk_order_items_product foreign key (product_id) references products(id)
);

create index idx_order_item_order on order_items (order_id);
create index idx_order_item_product_ordered_date on order_items (product_id, ordered_date);

create table payments (
                          id bigint auto_increment primary key,
                          order_id bigint not null,
                          amount bigint not null,
                          paid_at timestamp not null,
                          paid_date date not null,
                          paid_month int not null,
                          status varchar(20) not null,
                          constraint fk_payments_order foreign key (order_id) references orders(id)
);

create index idx_payment_paid_date_status on payments (paid_date, status);
create index idx_payment_order_paid_at on payments (order_id, paid_at);
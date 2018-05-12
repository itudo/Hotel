
--管理员表

create table admins(
  aid  int primary key, --管理员编号
  aname varchar2(20), --管理员姓名
  acount varchar2(20),  --登陆账号
  pwd varchar2(50), --登录密码
  job varchar2(10),  --管理员职位
  tel varchar2(50)    --手机号   
)

create sequence seq_admin start with 1000;

--食品类型表数据

create table foodtype1(
  ftid int primary key,     --食品类型编号
  tname varchar2(20) not null unique, --类型名字
  tdes varchar2(1500)     --类型描述  
)

create sequence seq_foodtype start with 1000;

--食品表 
create table food1(
  fid int primary key,    --食品编号
  fname varchar2(20),   --食品名
  fprice int,   --食品价格
  pic Blob,     --食品的图片
  fdes varchar2(1500),      --食品描述
  ftid int,         --食品类型编号
  picPath varchar2(300)           --食品图片路径
)

create sequence seq_food start with 1000;

--食品消费订单表
create table foodorder(
  foid varchar2(20)  primary key,      --食品订单编号
  rid varchar2(20),        --房间编号
  ftotal varchar2(1000),        --食品总计
  price int                  --食品价格
)

create sequence seq_foodorder_foid start with 1000;

  
--房间类型表
create table roomtype(
  rtid int primary key,   --房间类型编号
  rtname varchar2(20) unique, --房间类型名
  rprice int    --房间价格
)

insert into roomtype values(1001,'单人间',200)
insert into roomtype values(1002,'双人间',250)
insert into roomtype values(1003,'三人间',300)
insert into roomtype values(1004,'商务间',400)
insert into roomtype values(1005,'豪华间',500)
insert into roomtype values(1006,'套间',600)

--房间表 
create table room(
  rid varchar2(50) primary key,    --房间编号    
  rtid int,     --房间类型编号
  rstate varchar2(10) default ('空闲')  ,     --房间状态
  locate varchar2(5),    --房间所处位置
  picture blob                --房间图片
)


--客户表
create table custermer(
  cid int primary key,  --客户编号
  idnum varchar2(20) unique,   --客户身份证
  cname varchar2(200),   --客户姓名
  gender varchar2(50),   --性别
  tel varchar2(11)    --客户电话号码
)

create sequence seq_custermer  start with 1000;


--入住登记表
create table checkin(
    idnum varchar2(20),   --客户身份证
  rid varchar2(50),      --客户入住的房间号
  orderid int primary key,  --订单号
  checkintime date,   --入住时间
  checkouttime date,    --退房时间
  money int         --所交押金
  
)

create sequence seq_checkin_orderid start with 1000;
create table cellphone( 
       ID integer not null primary key, 
       BRAND varchar2(128),        --品牌
       MODEL varchar2(128) ,       --型号
       OS varchar2(128),           --操作系统 （IOS、Android、Windows Phone
       CPUBRAND varchar2(128),     --CPU品牌 （高通、联发科）
       RAM integer,                --内存（单位GB，整数）
       storage integer,            --存储容量 （单位GB，整数）
       COLOR varchar2(128),        --颜色 
       PRICE integer,              --价格 
       DESCRIPTION varchar2(512)  --描述
       
);

create sequence SEQ_cellphone
minvalue 1
maxvalue 999
start with 1
increment by 1
nocache;

create or replace trigger TRI_cellphone_ID
  before insert on cellphone 
  for each row
declare
  nextid number;
begin
  IF :new.ID IS NULL or :new.ID=0 THEN
    select SEQ_cellphone.nextval
    into nextid
    from sys.dual;
    :new.ID:=nextid;
  end if;
end TRI_cellphone_ID;  

select * from cellphone
insert into cellphone(BRAND,MODEL,OS,CPUBRAND,RAM,storage,COLOR,PRICE,DESCRIPTION) 
       values('小米','小米8','Android','高通',6,128,'黑色',2999,'性价比高');
insert into cellphone(BRAND,MODEL,OS,CPUBRAND,RAM,storage,COLOR,PRICE,DESCRIPTION) 
       values('Apple','iPhone X','IOS','苹果A10',3,64,'黑色',7588,'有点贵！');
insert into cellphone(BRAND,MODEL,OS,CPUBRAND,RAM,storage,COLOR,PRICE,DESCRIPTION) 
       values('OPPO','R15','Android','联发科',6,128,'星云',2699,'还不错');





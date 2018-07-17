create table cellphone( 
       ID integer not null primary key, 
       BRAND varchar2(128),        --Ʒ��
       MODEL varchar2(128) ,       --�ͺ�
       OS varchar2(128),           --����ϵͳ ��IOS��Android��Windows Phone
       CPUBRAND varchar2(128),     --CPUƷ�� ����ͨ�������ƣ�
       RAM integer,                --�ڴ棨��λGB��������
       storage integer,            --�洢���� ����λGB��������
       COLOR varchar2(128),        --��ɫ 
       PRICE integer,              --�۸� 
       DESCRIPTION varchar2(512)  --����
       
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
       values('С��','С��8','Android','��ͨ',6,128,'��ɫ',2999,'�Լ۱ȸ�');
insert into cellphone(BRAND,MODEL,OS,CPUBRAND,RAM,storage,COLOR,PRICE,DESCRIPTION) 
       values('Apple','iPhone X','IOS','ƻ��A10',3,64,'��ɫ',7588,'�е��');
insert into cellphone(BRAND,MODEL,OS,CPUBRAND,RAM,storage,COLOR,PRICE,DESCRIPTION) 
       values('OPPO','R15','Android','������',6,128,'����',2699,'������');





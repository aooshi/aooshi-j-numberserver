

-- drop table jnumberserver_store;
create table jnumberserver_store
(
	id int not null,
    v bigint default 0 not null,
    createtime datetime default null,
    primary key (id)
    
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数值服务器数值存储表';


-- drop table jnumberserver_user;
create table jnumberserver_user
(
	uid int not null,
    pwd char(32) default 0 not null,
    state int default 0 not null,
    createtime datetime default null,
    primary key (uid)
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数值服务器访问授权';


insert into jnumberserver_user set 
uid = 1,
pwd = 'BjSfz4WkKbn',
state = 0,
createtime=now();


insert into jnumberserver_user set 
uid = 2,
pwd = '42E3rPjTcetSS',
state = 0,
createtime=now();



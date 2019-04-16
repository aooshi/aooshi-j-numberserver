

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



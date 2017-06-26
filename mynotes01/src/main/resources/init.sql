drop table if exists note;
drop table if exists tags;
create table note(
	id integer identity primary key,
	username varchar(50),
	title varchar(250),
	message varchar(1400) not null,
	created_at timestamp not null,
	latitude double,
	longitude double
);

--,foreign key (username) references user_info(username)

create table tags(
	id integer identity primary key,
	tag_name varchar(100)
);

insert into tags(tag_name) values('消费');
insert into tags(tag_name) values('水果');
insert into tags(tag_name) values('超市');
insert into tags(tag_name) values('取钱');
insert into tags(tag_name) values('还钱');

create table note_with_tags(
	note_id integer,
	tag_id integer, 
	foreign key (note_id) references note(id),
	foreign key (tag_id) references tags(id)
	
);

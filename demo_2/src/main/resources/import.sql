-- 权限组
insert into SYS_RIGHT_CATS(RIGHT_CAT_ID, NAME, CAT_CODE) values(1, 'System', 'sys')
insert into SYS_RIGHT_CATS(RIGHT_CAT_ID, NAME, CAT_CODE) values(2, 'Help', 'help')

-- 权限
insert into SYS_RIGHTS(RIGHT_ID, RIGHT_CAT_ID, NAME, RIGHT_CODE, DESCRIPTION) values(1, 1, 'Right Category', 'rightcat', 'User right category management')
insert into SYS_RIGHTS(RIGHT_ID, RIGHT_CAT_ID, NAME, RIGHT_CODE, DESCRIPTION) values(2, 1, 'Right', 'right', 'User right management')
insert into SYS_RIGHTS(RIGHT_ID, RIGHT_CAT_ID, NAME, RIGHT_CODE, DESCRIPTION) values(3, 1, 'User', 'user', 'User account management')

insert into SYS_RIGHTS(RIGHT_ID, RIGHT_CAT_ID, NAME, RIGHT_CODE, DESCRIPTION) values(4, 2, 'Introduction', 'intro', 'Introduction of this demo')
insert into SYS_RIGHTS(RIGHT_ID, RIGHT_CAT_ID, NAME, RIGHT_CODE, DESCRIPTION) values(5, 2, 'Q & A', 'qa', 'Answer of common questions')

-- 用户
insert into SYS_USERS(USER_ID, USER_NAME, LOGIN_NAME, PASSWORD, EMAIL) values(1, 'Administrator', 'admin', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=', 'demo@aggrepoint.com')

-- 用户权限
insert into SYS_USER_RIGHTS(USER_ID, RIGHT_ID) values(1, 1)
insert into SYS_USER_RIGHTS(USER_ID, RIGHT_ID) values(1, 2)
insert into SYS_USER_RIGHTS(USER_ID, RIGHT_ID) values(1, 3)
insert into SYS_USER_RIGHTS(USER_ID, RIGHT_ID) values(1, 4)

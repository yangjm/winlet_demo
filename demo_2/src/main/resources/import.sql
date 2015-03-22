-- 权限组
insert into SYS_RIGHT_CATS(RIGHT_CAT_ID, NAME, CAT_CODE) values(1, '系统权限', 'sys')
insert into SYS_RIGHT_CATS(RIGHT_CAT_ID, NAME, CAT_CODE) values(2, '帮助权限', 'help')

-- 权限
insert into SYS_RIGHTS(RIGHT_ID, RIGHT_CAT_ID, NAME, RIGHT_CODE, DESCRIPTION) values(1, 1, '权限组管理', 'rightcat', '维护系统权限组')
insert into SYS_RIGHTS(RIGHT_ID, RIGHT_CAT_ID, NAME, RIGHT_CODE, DESCRIPTION) values(2, 1, '权限管理', 'right', '管理权限组中的权限')
insert into SYS_RIGHTS(RIGHT_ID, RIGHT_CAT_ID, NAME, RIGHT_CODE, DESCRIPTION) values(3, 1, '用户管理', 'user', '用户账号管理')

insert into SYS_RIGHTS(RIGHT_ID, RIGHT_CAT_ID, NAME, RIGHT_CODE, DESCRIPTION) values(4, 2, '演示介绍', 'intro', '简单介绍本演示')
insert into SYS_RIGHTS(RIGHT_ID, RIGHT_CAT_ID, NAME, RIGHT_CODE, DESCRIPTION) values(5, 2, '问题解答', 'qa', '常见问题解答')

-- 用户
insert into SYS_USERS(USER_ID, USER_NAME, LOGIN_NAME, PASSWORD, EMAIL) values(1, 'Administrator', 'admin', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=', 'demo@aggrepoint.com')

-- 用户权限
insert into SYS_USER_RIGHTS(USER_ID, RIGHT_ID) values(1, 1)
insert into SYS_USER_RIGHTS(USER_ID, RIGHT_ID) values(1, 2)
insert into SYS_USER_RIGHTS(USER_ID, RIGHT_ID) values(1, 3)
insert into SYS_USER_RIGHTS(USER_ID, RIGHT_ID) values(1, 4)

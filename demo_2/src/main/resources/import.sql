-- 权限组
insert into SYS_RIGHT_CATS(RIGHT_CAT_ID, NAME, CAT_CODE) values(1, '系统', 'sys')
insert into SYS_RIGHT_CATS(RIGHT_CAT_ID, NAME, CAT_CODE) values(2, '帮助', 'help')

-- 权限
insert into SYS_RIGHTS(RIGHT_ID, RIGHT_CAT_ID, NAME, RIGHT_CODE, DESCRIPTION) values(1, 1, '权限管理', 'right', '系统权限组及权限管理')
insert into SYS_RIGHTS(RIGHT_ID, RIGHT_CAT_ID, NAME, RIGHT_CODE, DESCRIPTION) values(2, 1, '角色管理', 'role', '系统角色管理')
insert into SYS_RIGHTS(RIGHT_ID, RIGHT_CAT_ID, NAME, RIGHT_CODE, DESCRIPTION) values(3, 1, '用户管理', 'user', '系统用户管理')

insert into SYS_RIGHTS(RIGHT_ID, RIGHT_CAT_ID, NAME, RIGHT_CODE, DESCRIPTION) values(4, 2, '演示介绍', 'intro', '介绍演示中使用的框架功能')
insert into SYS_RIGHTS(RIGHT_ID, RIGHT_CAT_ID, NAME, RIGHT_CODE, DESCRIPTION) values(5, 2, '常见问题解答', 'qa', '开发人员常见问题解答')

-- 系统管理员
insert into SYS_ROLES(ROLE_ID, NAME, DESCRIPTION) values (1, '系统管理员', '可以访问系统中所有功能');
insert into SYS_ROLE_RIGHTS(ROLE_ID, RIGHT_ID) values(1, 1)
insert into SYS_ROLE_RIGHTS(ROLE_ID, RIGHT_ID) values(1, 2)
insert into SYS_ROLE_RIGHTS(ROLE_ID, RIGHT_ID) values(1, 3)
insert into SYS_ROLE_RIGHTS(ROLE_ID, RIGHT_ID) values(1, 4)
insert into SYS_ROLE_RIGHTS(ROLE_ID, RIGHT_ID) values(1, 5)

-- 用户管理员
insert into SYS_ROLES(ROLE_ID, NAME, DESCRIPTION) values (2, '用户管理员', '可以进行用户管理，但不能配置系统权限和角色');
insert into SYS_ROLE_RIGHTS(ROLE_ID, RIGHT_ID) values(2, 3)
insert into SYS_ROLE_RIGHTS(ROLE_ID, RIGHT_ID) values(2, 4)
insert into SYS_ROLE_RIGHTS(ROLE_ID, RIGHT_ID) values(2, 5)

-- 普通访客
insert into SYS_ROLES(ROLE_ID, NAME, DESCRIPTION) values (3, '普通访客', '只能访问帮助');
insert into SYS_ROLE_RIGHTS(ROLE_ID, RIGHT_ID) values(3, 4)
insert into SYS_ROLE_RIGHTS(ROLE_ID, RIGHT_ID) values(3, 5)

-- 系统管理员用户
insert into SYS_USERS(USER_ID, USER_NAME, LOGIN_NAME, PASSWORD, EMAIL) values(1, '阿土仔', 'admin', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=', 'demo_admin@aggrepoint.com')
insert into SYS_USER_ROLES(USER_ID, ROLE_ID) values(1, 1)

-- 用户管理员
insert into SYS_USERS(USER_ID, USER_NAME, LOGIN_NAME, PASSWORD, EMAIL) values(2, '孙小美', 'user', 'BPiZbadjt6lpsQKO4wB1aerzpjVIbdqyEdUSyFud+Ps=', 'demo_user@aggrepoint.com')
insert into SYS_USER_ROLES(USER_ID, ROLE_ID) values(2, 2)

-- 普通访客用户
insert into SYS_USERS(USER_ID, USER_NAME, LOGIN_NAME, PASSWORD, EMAIL) values(3, '钱夫人', 'visitor', 'XxT55tgPgCplJpgE8lUu+YifLHzOxQZyFOWKHkjgs/8=', 'demo_user@aggrepoint.com')
insert into SYS_USER_ROLES(USER_ID, ROLE_ID) values(3, 3)

-- Ȩ����
insert into SYS_RIGHT_CATS(RIGHT_CAT_ID, NAME, CAT_CODE) values(1, 'ϵͳ', 'sys')
insert into SYS_RIGHT_CATS(RIGHT_CAT_ID, NAME, CAT_CODE) values(2, '����', 'help')

-- Ȩ��
insert into SYS_RIGHTS(RIGHT_ID, RIGHT_CAT_ID, NAME, RIGHT_CODE, DESCRIPTION) values(1, 1, 'Ȩ�޹���', 'right', 'ϵͳȨ���鼰Ȩ�޹���')
insert into SYS_RIGHTS(RIGHT_ID, RIGHT_CAT_ID, NAME, RIGHT_CODE, DESCRIPTION) values(2, 1, '��ɫ����', 'role', 'ϵͳ��ɫ����')
insert into SYS_RIGHTS(RIGHT_ID, RIGHT_CAT_ID, NAME, RIGHT_CODE, DESCRIPTION) values(3, 1, '�û�����', 'user', 'ϵͳ�û�����')

insert into SYS_RIGHTS(RIGHT_ID, RIGHT_CAT_ID, NAME, RIGHT_CODE, DESCRIPTION) values(4, 2, '��ʾ����', 'intro', '������ʾ��ʹ�õĿ�ܹ���')
insert into SYS_RIGHTS(RIGHT_ID, RIGHT_CAT_ID, NAME, RIGHT_CODE, DESCRIPTION) values(5, 2, '����������', 'qa', '������Ա����������')

-- ϵͳ����Ա
insert into SYS_ROLES(ROLE_ID, NAME, DESCRIPTION) values (1, 'ϵͳ����Ա', '���Է���ϵͳ�����й���');
insert into SYS_ROLE_RIGHTS(ROLE_ID, RIGHT_ID) values(1, 1)
insert into SYS_ROLE_RIGHTS(ROLE_ID, RIGHT_ID) values(1, 2)
insert into SYS_ROLE_RIGHTS(ROLE_ID, RIGHT_ID) values(1, 3)
insert into SYS_ROLE_RIGHTS(ROLE_ID, RIGHT_ID) values(1, 4)
insert into SYS_ROLE_RIGHTS(ROLE_ID, RIGHT_ID) values(1, 5)

-- �û�����Ա
insert into SYS_ROLES(ROLE_ID, NAME, DESCRIPTION) values (2, '�û�����Ա', '���Խ����û���������������ϵͳȨ�޺ͽ�ɫ');
insert into SYS_ROLE_RIGHTS(ROLE_ID, RIGHT_ID) values(2, 3)
insert into SYS_ROLE_RIGHTS(ROLE_ID, RIGHT_ID) values(2, 4)
insert into SYS_ROLE_RIGHTS(ROLE_ID, RIGHT_ID) values(2, 5)

-- ��ͨ�ÿ�
insert into SYS_ROLES(ROLE_ID, NAME, DESCRIPTION) values (3, '��ͨ�ÿ�', 'ֻ�ܷ��ʰ���');
insert into SYS_ROLE_RIGHTS(ROLE_ID, RIGHT_ID) values(3, 4)
insert into SYS_ROLE_RIGHTS(ROLE_ID, RIGHT_ID) values(3, 5)

-- ϵͳ����Ա�û�
insert into SYS_USERS(USER_ID, USER_NAME, LOGIN_NAME, PASSWORD, EMAIL) values(1, '������', 'admin', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=', 'demo_admin@aggrepoint.com')
insert into SYS_USER_ROLES(USER_ID, ROLE_ID) values(1, 1)

-- �û�����Ա
insert into SYS_USERS(USER_ID, USER_NAME, LOGIN_NAME, PASSWORD, EMAIL) values(2, '��С��', 'user', 'BPiZbadjt6lpsQKO4wB1aerzpjVIbdqyEdUSyFud+Ps=', 'demo_user@aggrepoint.com')
insert into SYS_USER_ROLES(USER_ID, ROLE_ID) values(2, 2)

-- ��ͨ�ÿ��û�
insert into SYS_USERS(USER_ID, USER_NAME, LOGIN_NAME, PASSWORD, EMAIL) values(3, 'Ǯ����', 'visitor', 'XxT55tgPgCplJpgE8lUu+YifLHzOxQZyFOWKHkjgs/8=', 'demo_user@aggrepoint.com')
insert into SYS_USER_ROLES(USER_ID, ROLE_ID) values(3, 3)

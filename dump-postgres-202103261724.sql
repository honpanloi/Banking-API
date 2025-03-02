PGDMP                         y           postgres    12.3    12.3 ?    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    13318    postgres    DATABASE     �   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE postgres;
                postgres    false            �           0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                   postgres    false    2962                        2615    41005    my_bank_app    SCHEMA        CREATE SCHEMA my_bank_app;
    DROP SCHEMA my_bank_app;
                postgres    false            �            1259    41006    account    TABLE     h  CREATE TABLE my_bank_app.account (
    number bigint NOT NULL,
    account_type character varying(20) NOT NULL,
    date_created character varying(45) NOT NULL,
    owner_id integer,
    status character varying(20) DEFAULT 'not_yet_approved'::character varying NOT NULL,
    approved_by bigint,
    rejected_by bigint,
    current_balance double precision
);
     DROP TABLE my_bank_app.account;
       my_bank_app         heap    postgres    false    11            �            1259    41203    account_number_seq    SEQUENCE     �   CREATE SEQUENCE my_bank_app.account_number_seq
    START WITH 200000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE my_bank_app.account_number_seq;
       my_bank_app          postgres    false    209    11            �           0    0    account_number_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE my_bank_app.account_number_seq OWNED BY my_bank_app.account.number;
          my_bank_app          postgres    false    219            �            1259    41117    account_status    TABLE     W   CREATE TABLE my_bank_app.account_status (
    status character varying(20) NOT NULL
);
 '   DROP TABLE my_bank_app.account_status;
       my_bank_app         heap    postgres    false    11            �            1259    41019    account_type    TABLE     9  CREATE TABLE my_bank_app.account_type (
    type character varying(20) NOT NULL,
    monthly_interest double precision NOT NULL,
    credit_score_req integer,
    min_balance_req double precision,
    overdrawn_amount double precision,
    annual_service_fee double precision,
    monthly_fee double precision
);
 %   DROP TABLE my_bank_app.account_type;
       my_bank_app         heap    postgres    false    11            �            1259    41009    customer    TABLE     �  CREATE TABLE my_bank_app.customer (
    id bigint NOT NULL,
    first_name character varying(20) NOT NULL,
    salutation character varying(10),
    dob date NOT NULL,
    address character varying(80) NOT NULL,
    phone1 character varying NOT NULL,
    phone2 character varying,
    email character varying(50) NOT NULL,
    basic_checking_acc_id bigint,
    basic_saving_acc_id bigint,
    prem_checking_acc_id bigint,
    prem_saving_acc_id bigint,
    login_user_name character varying(20),
    login_password character varying(20) DEFAULT 'defaultPassword'::character varying,
    credit_score integer NOT NULL,
    last_name character varying(20) NOT NULL,
    ssn integer NOT NULL,
    register_by_employee bigint,
    CONSTRAINT customer_check_lowest_credit_score CHECK ((credit_score >= 299)),
    CONSTRAINT customer_credit_score_check1 CHECK ((credit_score <= 850)),
    CONSTRAINT customer_dob_check CHECK ((dob >= '1890-01-01'::date))
);
 !   DROP TABLE my_bank_app.customer;
       my_bank_app         heap    postgres    false    11            �            1259    41189    customer_number_seq    SEQUENCE     �   CREATE SEQUENCE my_bank_app.customer_number_seq
    START WITH 10000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE my_bank_app.customer_number_seq;
       my_bank_app          postgres    false    210    11            �           0    0    customer_number_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE my_bank_app.customer_number_seq OWNED BY my_bank_app.customer.id;
          my_bank_app          postgres    false    218            �            1259    41156    employee    TABLE     �   CREATE TABLE my_bank_app.employee (
    id bigint NOT NULL,
    login_user_name character varying(20) NOT NULL,
    login_password character varying(20) NOT NULL,
    name character varying(30) NOT NULL
);
 !   DROP TABLE my_bank_app.employee;
       my_bank_app         heap    postgres    false    11            �            1259    41096    transaction    TABLE     �  CREATE TABLE my_bank_app.transaction (
    trans_number bigint NOT NULL,
    type character varying(20) NOT NULL,
    withdraw_from bigint,
    deposit_to bigint,
    status character varying(20) NOT NULL,
    time_requested character varying(40) NOT NULL,
    time_completed character varying(40),
    amount double precision NOT NULL,
    balance_after_withdraw double precision,
    balance_after_deposit double precision,
    initiator_acc bigint
);
 $   DROP TABLE my_bank_app.transaction;
       my_bank_app         heap    postgres    false    11            �            1259    41159    transaction_status    TABLE     [   CREATE TABLE my_bank_app.transaction_status (
    status character varying(20) NOT NULL
);
 +   DROP TABLE my_bank_app.transaction_status;
       my_bank_app         heap    postgres    false    11            �            1259    41112    transaction_type    TABLE     W   CREATE TABLE my_bank_app.transaction_type (
    type character varying(20) NOT NULL
);
 )   DROP TABLE my_bank_app.transaction_type;
       my_bank_app         heap    postgres    false    11            �            1259    41094    transation_number_seq    SEQUENCE     �   CREATE SEQUENCE my_bank_app.transation_number_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE my_bank_app.transation_number_seq;
       my_bank_app          postgres    false    213    11            �           0    0    transation_number_seq    SEQUENCE OWNED BY     `   ALTER SEQUENCE my_bank_app.transation_number_seq OWNED BY my_bank_app.transaction.trans_number;
          my_bank_app          postgres    false    212            �            1259    41330    user_name_sel    SEQUENCE     �   CREATE SEQUENCE my_bank_app.user_name_sel
    START WITH 100000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE my_bank_app.user_name_sel;
       my_bank_app          postgres    false    210    11            �           0    0    user_name_sel    SEQUENCE OWNED BY     X   ALTER SEQUENCE my_bank_app.user_name_sel OWNED BY my_bank_app.customer.login_user_name;
          my_bank_app          postgres    false    220            �
           2604    41260    account number    DEFAULT     z   ALTER TABLE ONLY my_bank_app.account ALTER COLUMN number SET DEFAULT nextval('my_bank_app.account_number_seq'::regclass);
 B   ALTER TABLE my_bank_app.account ALTER COLUMN number DROP DEFAULT;
       my_bank_app          postgres    false    219    209            �
           2604    41261    customer id    DEFAULT     x   ALTER TABLE ONLY my_bank_app.customer ALTER COLUMN id SET DEFAULT nextval('my_bank_app.customer_number_seq'::regclass);
 ?   ALTER TABLE my_bank_app.customer ALTER COLUMN id DROP DEFAULT;
       my_bank_app          postgres    false    218    210            �
           2604    41332    customer login_user_name    DEFAULT        ALTER TABLE ONLY my_bank_app.customer ALTER COLUMN login_user_name SET DEFAULT nextval('my_bank_app.user_name_sel'::regclass);
 L   ALTER TABLE my_bank_app.customer ALTER COLUMN login_user_name DROP DEFAULT;
       my_bank_app          postgres    false    220    210            �
           2604    41099    transaction trans_number    DEFAULT     �   ALTER TABLE ONLY my_bank_app.transaction ALTER COLUMN trans_number SET DEFAULT nextval('my_bank_app.transation_number_seq'::regclass);
 L   ALTER TABLE my_bank_app.transaction ALTER COLUMN trans_number DROP DEFAULT;
       my_bank_app          postgres    false    212    213    213            �          0    41006    account 
   TABLE DATA           �   COPY my_bank_app.account (number, account_type, date_created, owner_id, status, approved_by, rejected_by, current_balance) FROM stdin;
    my_bank_app          postgres    false    209            �          0    41117    account_status 
   TABLE DATA           5   COPY my_bank_app.account_status (status) FROM stdin;
    my_bank_app          postgres    false    215            �          0    41019    account_type 
   TABLE DATA           �   COPY my_bank_app.account_type (type, monthly_interest, credit_score_req, min_balance_req, overdrawn_amount, annual_service_fee, monthly_fee) FROM stdin;
    my_bank_app          postgres    false    211            �          0    41009    customer 
   TABLE DATA             COPY my_bank_app.customer (id, first_name, salutation, dob, address, phone1, phone2, email, basic_checking_acc_id, basic_saving_acc_id, prem_checking_acc_id, prem_saving_acc_id, login_user_name, login_password, credit_score, last_name, ssn, register_by_employee) FROM stdin;
    my_bank_app          postgres    false    210            �          0    41156    employee 
   TABLE DATA           R   COPY my_bank_app.employee (id, login_user_name, login_password, name) FROM stdin;
    my_bank_app          postgres    false    216            �          0    41096    transaction 
   TABLE DATA           �   COPY my_bank_app.transaction (trans_number, type, withdraw_from, deposit_to, status, time_requested, time_completed, amount, balance_after_withdraw, balance_after_deposit, initiator_acc) FROM stdin;
    my_bank_app          postgres    false    213            �          0    41159    transaction_status 
   TABLE DATA           9   COPY my_bank_app.transaction_status (status) FROM stdin;
    my_bank_app          postgres    false    217            �          0    41112    transaction_type 
   TABLE DATA           5   COPY my_bank_app.transaction_type (type) FROM stdin;
    my_bank_app          postgres    false    214            �           0    0    account_number_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('my_bank_app.account_number_seq', 200026, true);
          my_bank_app          postgres    false    219            �           0    0    customer_number_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('my_bank_app.customer_number_seq', 10015, true);
          my_bank_app          postgres    false    218            �           0    0    transation_number_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('my_bank_app.transation_number_seq', 47, true);
          my_bank_app          postgres    false    212            �           0    0    user_name_sel    SEQUENCE SET     E   SELECT pg_catalog.setval('my_bank_app.user_name_sel', 100000, true);
          my_bank_app          postgres    false    220            �
           2606    41015    account account_pk 
   CONSTRAINT     Y   ALTER TABLE ONLY my_bank_app.account
    ADD CONSTRAINT account_pk PRIMARY KEY (number);
 A   ALTER TABLE ONLY my_bank_app.account DROP CONSTRAINT account_pk;
       my_bank_app            postgres    false    209            �
           2606    41150     account_status account_status_pk 
   CONSTRAINT     g   ALTER TABLE ONLY my_bank_app.account_status
    ADD CONSTRAINT account_status_pk PRIMARY KEY (status);
 O   ALTER TABLE ONLY my_bank_app.account_status DROP CONSTRAINT account_status_pk;
       my_bank_app            postgres    false    215            �
           2606    41023    account_type account_type_pk 
   CONSTRAINT     a   ALTER TABLE ONLY my_bank_app.account_type
    ADD CONSTRAINT account_type_pk PRIMARY KEY (type);
 K   ALTER TABLE ONLY my_bank_app.account_type DROP CONSTRAINT account_type_pk;
       my_bank_app            postgres    false    211            �
           2606    41232 %   customer customer_login_user_name_key 
   CONSTRAINT     p   ALTER TABLE ONLY my_bank_app.customer
    ADD CONSTRAINT customer_login_user_name_key UNIQUE (login_user_name);
 T   ALTER TABLE ONLY my_bank_app.customer DROP CONSTRAINT customer_login_user_name_key;
       my_bank_app            postgres    false    210            �
           2606    41319    customer customer_un_ssn 
   CONSTRAINT     W   ALTER TABLE ONLY my_bank_app.customer
    ADD CONSTRAINT customer_un_ssn UNIQUE (ssn);
 G   ALTER TABLE ONLY my_bank_app.customer DROP CONSTRAINT customer_un_ssn;
       my_bank_app            postgres    false    210            �
           2606    41192    customer customers_pk 
   CONSTRAINT     X   ALTER TABLE ONLY my_bank_app.customer
    ADD CONSTRAINT customers_pk PRIMARY KEY (id);
 D   ALTER TABLE ONLY my_bank_app.customer DROP CONSTRAINT customers_pk;
       my_bank_app            postgres    false    210            �
           2606    41212    employee employee_pk 
   CONSTRAINT     W   ALTER TABLE ONLY my_bank_app.employee
    ADD CONSTRAINT employee_pk PRIMARY KEY (id);
 C   ALTER TABLE ONLY my_bank_app.employee DROP CONSTRAINT employee_pk;
       my_bank_app            postgres    false    216            �
           2606    41101    transaction transation_pk 
   CONSTRAINT     f   ALTER TABLE ONLY my_bank_app.transaction
    ADD CONSTRAINT transation_pk PRIMARY KEY (trans_number);
 H   ALTER TABLE ONLY my_bank_app.transaction DROP CONSTRAINT transation_pk;
       my_bank_app            postgres    false    213            �
           2606    41163 '   transaction_status transation_status_pk 
   CONSTRAINT     n   ALTER TABLE ONLY my_bank_app.transaction_status
    ADD CONSTRAINT transation_status_pk PRIMARY KEY (status);
 V   ALTER TABLE ONLY my_bank_app.transaction_status DROP CONSTRAINT transation_status_pk;
       my_bank_app            postgres    false    217            �
           2606    41123 #   transaction_type transation_type_pk 
   CONSTRAINT     h   ALTER TABLE ONLY my_bank_app.transaction_type
    ADD CONSTRAINT transation_type_pk PRIMARY KEY (type);
 R   ALTER TABLE ONLY my_bank_app.transaction_type DROP CONSTRAINT transation_type_pk;
       my_bank_app            postgres    false    214            �
           2606    41193    account account_fk    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.account
    ADD CONSTRAINT account_fk FOREIGN KEY (owner_id) REFERENCES my_bank_app.customer(id) ON UPDATE CASCADE ON DELETE SET NULL;
 A   ALTER TABLE ONLY my_bank_app.account DROP CONSTRAINT account_fk;
       my_bank_app          postgres    false    209    2791    210            �
           2606    41339    account account_fk_apprby    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.account
    ADD CONSTRAINT account_fk_apprby FOREIGN KEY (approved_by) REFERENCES my_bank_app.employee(id);
 H   ALTER TABLE ONLY my_bank_app.account DROP CONSTRAINT account_fk_apprby;
       my_bank_app          postgres    false    2801    216    209            �
           2606    41348    account account_fk_rejeby    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.account
    ADD CONSTRAINT account_fk_rejeby FOREIGN KEY (rejected_by) REFERENCES my_bank_app.employee(id);
 H   ALTER TABLE ONLY my_bank_app.account DROP CONSTRAINT account_fk_rejeby;
       my_bank_app          postgres    false    2801    216    209            �
           2606    41273    account account_fk_status    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.account
    ADD CONSTRAINT account_fk_status FOREIGN KEY (status) REFERENCES my_bank_app.account_status(status);
 H   ALTER TABLE ONLY my_bank_app.account DROP CONSTRAINT account_fk_status;
       my_bank_app          postgres    false    209    215    2799            �
           2606    41262    account account_fk_type    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.account
    ADD CONSTRAINT account_fk_type FOREIGN KEY (account_type) REFERENCES my_bank_app.account_type(type);
 F   ALTER TABLE ONLY my_bank_app.account DROP CONSTRAINT account_fk_type;
       my_bank_app          postgres    false    211    209    2793            �
           2606    41134    customer customer_fk_bc    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.customer
    ADD CONSTRAINT customer_fk_bc FOREIGN KEY (basic_checking_acc_id) REFERENCES my_bank_app.account(number) ON UPDATE CASCADE ON DELETE SET NULL;
 F   ALTER TABLE ONLY my_bank_app.customer DROP CONSTRAINT customer_fk_bc;
       my_bank_app          postgres    false    2785    210    209            �
           2606    41129    customer customer_fk_bs    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.customer
    ADD CONSTRAINT customer_fk_bs FOREIGN KEY (basic_saving_acc_id) REFERENCES my_bank_app.account(number) ON UPDATE CASCADE ON DELETE SET NULL;
 F   ALTER TABLE ONLY my_bank_app.customer DROP CONSTRAINT customer_fk_bs;
       my_bank_app          postgres    false    210    2785    209            �
           2606    41139    customer customer_fk_pc    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.customer
    ADD CONSTRAINT customer_fk_pc FOREIGN KEY (prem_checking_acc_id) REFERENCES my_bank_app.account(number) ON UPDATE CASCADE ON DELETE SET NULL;
 F   ALTER TABLE ONLY my_bank_app.customer DROP CONSTRAINT customer_fk_pc;
       my_bank_app          postgres    false    210    2785    209            �
           2606    41144    customer customer_fk_ps    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.customer
    ADD CONSTRAINT customer_fk_ps FOREIGN KEY (prem_saving_acc_id) REFERENCES my_bank_app.account(number) ON UPDATE CASCADE ON DELETE SET NULL;
 F   ALTER TABLE ONLY my_bank_app.customer DROP CONSTRAINT customer_fk_ps;
       my_bank_app          postgres    false    2785    209    210            �
           2606    41333    customer customer_fk_rbe    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.customer
    ADD CONSTRAINT customer_fk_rbe FOREIGN KEY (register_by_employee) REFERENCES my_bank_app.employee(id);
 G   ALTER TABLE ONLY my_bank_app.customer DROP CONSTRAINT customer_fk_rbe;
       my_bank_app          postgres    false    210    2801    216                       2606    41233    transaction transaction_fk    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.transaction
    ADD CONSTRAINT transaction_fk FOREIGN KEY (initiator_acc) REFERENCES my_bank_app.account(number);
 I   ALTER TABLE ONLY my_bank_app.transaction DROP CONSTRAINT transaction_fk;
       my_bank_app          postgres    false    2785    213    209            �
           2606    41107    transaction transation_fk_dt    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.transaction
    ADD CONSTRAINT transation_fk_dt FOREIGN KEY (deposit_to) REFERENCES my_bank_app.account(number);
 K   ALTER TABLE ONLY my_bank_app.transaction DROP CONSTRAINT transation_fk_dt;
       my_bank_app          postgres    false    213    209    2785                        2606    41164    transaction transation_fk_ts    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.transaction
    ADD CONSTRAINT transation_fk_ts FOREIGN KEY (status) REFERENCES my_bank_app.transaction_status(status) ON UPDATE CASCADE;
 K   ALTER TABLE ONLY my_bank_app.transaction DROP CONSTRAINT transation_fk_ts;
       my_bank_app          postgres    false    213    217    2803                       2606    41206    transaction transation_fk_ty    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.transaction
    ADD CONSTRAINT transation_fk_ty FOREIGN KEY (type) REFERENCES my_bank_app.transaction_type(type) ON UPDATE CASCADE;
 K   ALTER TABLE ONLY my_bank_app.transaction DROP CONSTRAINT transation_fk_ty;
       my_bank_app          postgres    false    214    213    2797            �
           2606    41102    transaction transation_fk_wf    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.transaction
    ADD CONSTRAINT transation_fk_wf FOREIGN KEY (withdraw_from) REFERENCES my_bank_app.account(number);
 K   ALTER TABLE ONLY my_bank_app.transaction DROP CONSTRAINT transation_fk_wf;
       my_bank_app          postgres    false    2785    213    209            �   b  x����N�@�������/ث�����&�U�J�ԧw������=@���wi��o�Ųxu���|a�1�e��Ğ��ӕ,��"jo���e��g�r���l�c� �Т�Jt�be�,�Y�M]�ݪu�j�^7Su���N���t�.���^׳��V�VG���6ߟ���3�Bf	��:���#Tx�T�e\q���-�a�Y��h��!�*����"�ݛ+�c1F���B�ѭJ�=k�X�V�`146(��G`UfQ�=�b�H���cpR��d�ȴO�~�1RL�P�i�2�*�"/�#��!����[�����y�(��Y�.��1Z$Q}LI�      �   9   x�KL.�,K�J��/NM�J+ʯJ����/��L-�O,((�/��f�&� 1z\\\ 	�m      �   Z   x�KJ,�L�O�HM���K�4����4450�a �+	��8�,�g``Rc �\E�����-A����� ��ݐ����"���� ZA n      �   �  x���_K�0 ��ۧ��F.���)"�8���/٬����V�o�%i�
���#)k�_�rE�y	�ݺ��`����Xh@j��u=��ۺ�ٍ[׬ゝ5�[A���.je���7'��,ڵ�7_�Q)��T�8���u� 
%U)���FH.�\��.����\�\h>��5BjSf�N.��`U�q<
��.l���$��h#���}ED!��*iM���Җ��r��/�=H�$E�j�6A��s��=c��l�d{jn���`�c�l~���r�<���k׬����qm*ޕ�����kϞ�T�v�J�4C t+���G�������G���o]׽��U�	�N��p���� �~��c���8�.f�c`5���v9�2�,��/*�X�$T��N�NYE�ѕ�H�H�ݟԃ "ʝ��v�4�	T��A��\���;5q?�F�ST�      �   +   x��4000�4ᕘ��e	4�4ᑚ������� ��	u      �   �  x����n�0���S���h[��6`���.��� �S����(�Nm�v�9A�K~��_�!9�v���ꔠ��?�<_^��s���2���*��3��=f���<~u_�q�����Ǿz=����S1L��*��bk�m^��(\�,�=
d!��)�<�r?n� �~����!��s��;5�K9��唋ת��_�ϻ:H����o���C*8J�`,�����{-6D��������{�r�}8���O�6����Bgf\b*A�9�-�^�f�FWS4��1VA@���]rQV�9
�����s*� �v\�UK��W�ڒ˾e��(+��uY�^� �ta��d����(���!xo���QV���\.1h��d�w���Z��ȭUyY��e	���Y��6�J'�Մ�k���5av��b��j�������m�)ٔ�F5��������ɠ�����#�F��@���D�A5��'Q��L����5�s'1FU_}b�9��̊&B2�U�>����[0+��$c��|�s��ī�/Q���0�C��_ƙ5�8�p1��>L@qf�����T���D�ќpJ[�)� �8ˀg���(a$��n��px���&���0��9yT�u�	��[aXZ�U?�̩C�@pmO�\F�W�v�7�`O���]
�1w��7(���uW?U��I�6�����]%Wk�s_�a�w��0�Ւ�c�5Ij�ډ+�b���]۠�t,�Y.saه2w���:A���ށ�,>��{wW,���Q�v��]$̚�-���&�zA���"�7 �����Q�����a(�ڷ^��腮%ѫIнL��$�?H��V����e�*g�<
���h�Z8���˭.���=#�C���z��Q.��^B�r3��.��-�����v���*XAe      �   &   x�+H�K��K�JN�KN�IM�J��-�I-�b���� �#
�      �   ,   x�+)J�+NK-�J,ɍOI-�/�,Is�3K2R�˹b���� ?5      ?    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    13318    postgres    DATABASE     �   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE postgres;
                postgres    false            �           0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                   postgres    false    2962                        2615    41005    my_bank_app    SCHEMA        CREATE SCHEMA my_bank_app;
    DROP SCHEMA my_bank_app;
                postgres    false            �            1259    41006    account    TABLE     h  CREATE TABLE my_bank_app.account (
    number bigint NOT NULL,
    account_type character varying(20) NOT NULL,
    date_created character varying(45) NOT NULL,
    owner_id integer,
    status character varying(20) DEFAULT 'not_yet_approved'::character varying NOT NULL,
    approved_by bigint,
    rejected_by bigint,
    current_balance double precision
);
     DROP TABLE my_bank_app.account;
       my_bank_app         heap    postgres    false    11            �            1259    41203    account_number_seq    SEQUENCE     �   CREATE SEQUENCE my_bank_app.account_number_seq
    START WITH 200000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE my_bank_app.account_number_seq;
       my_bank_app          postgres    false    209    11            �           0    0    account_number_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE my_bank_app.account_number_seq OWNED BY my_bank_app.account.number;
          my_bank_app          postgres    false    219            �            1259    41117    account_status    TABLE     W   CREATE TABLE my_bank_app.account_status (
    status character varying(20) NOT NULL
);
 '   DROP TABLE my_bank_app.account_status;
       my_bank_app         heap    postgres    false    11            �            1259    41019    account_type    TABLE     9  CREATE TABLE my_bank_app.account_type (
    type character varying(20) NOT NULL,
    monthly_interest double precision NOT NULL,
    credit_score_req integer,
    min_balance_req double precision,
    overdrawn_amount double precision,
    annual_service_fee double precision,
    monthly_fee double precision
);
 %   DROP TABLE my_bank_app.account_type;
       my_bank_app         heap    postgres    false    11            �            1259    41009    customer    TABLE     �  CREATE TABLE my_bank_app.customer (
    id bigint NOT NULL,
    first_name character varying(20) NOT NULL,
    salutation character varying(10),
    dob date NOT NULL,
    address character varying(80) NOT NULL,
    phone1 character varying NOT NULL,
    phone2 character varying,
    email character varying(50) NOT NULL,
    basic_checking_acc_id bigint,
    basic_saving_acc_id bigint,
    prem_checking_acc_id bigint,
    prem_saving_acc_id bigint,
    login_user_name character varying(20),
    login_password character varying(20) DEFAULT 'defaultPassword'::character varying,
    credit_score integer NOT NULL,
    last_name character varying(20) NOT NULL,
    ssn integer NOT NULL,
    register_by_employee bigint,
    CONSTRAINT customer_check_lowest_credit_score CHECK ((credit_score >= 299)),
    CONSTRAINT customer_credit_score_check1 CHECK ((credit_score <= 850)),
    CONSTRAINT customer_dob_check CHECK ((dob >= '1890-01-01'::date))
);
 !   DROP TABLE my_bank_app.customer;
       my_bank_app         heap    postgres    false    11            �            1259    41189    customer_number_seq    SEQUENCE     �   CREATE SEQUENCE my_bank_app.customer_number_seq
    START WITH 10000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE my_bank_app.customer_number_seq;
       my_bank_app          postgres    false    210    11            �           0    0    customer_number_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE my_bank_app.customer_number_seq OWNED BY my_bank_app.customer.id;
          my_bank_app          postgres    false    218            �            1259    41156    employee    TABLE     �   CREATE TABLE my_bank_app.employee (
    id bigint NOT NULL,
    login_user_name character varying(20) NOT NULL,
    login_password character varying(20) NOT NULL,
    name character varying(30) NOT NULL
);
 !   DROP TABLE my_bank_app.employee;
       my_bank_app         heap    postgres    false    11            �            1259    41096    transaction    TABLE     �  CREATE TABLE my_bank_app.transaction (
    trans_number bigint NOT NULL,
    type character varying(20) NOT NULL,
    withdraw_from bigint,
    deposit_to bigint,
    status character varying(20) NOT NULL,
    time_requested character varying(40) NOT NULL,
    time_completed character varying(40),
    amount double precision NOT NULL,
    balance_after_withdraw double precision,
    balance_after_deposit double precision,
    initiator_acc bigint
);
 $   DROP TABLE my_bank_app.transaction;
       my_bank_app         heap    postgres    false    11            �            1259    41159    transaction_status    TABLE     [   CREATE TABLE my_bank_app.transaction_status (
    status character varying(20) NOT NULL
);
 +   DROP TABLE my_bank_app.transaction_status;
       my_bank_app         heap    postgres    false    11            �            1259    41112    transaction_type    TABLE     W   CREATE TABLE my_bank_app.transaction_type (
    type character varying(20) NOT NULL
);
 )   DROP TABLE my_bank_app.transaction_type;
       my_bank_app         heap    postgres    false    11            �            1259    41094    transation_number_seq    SEQUENCE     �   CREATE SEQUENCE my_bank_app.transation_number_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE my_bank_app.transation_number_seq;
       my_bank_app          postgres    false    213    11            �           0    0    transation_number_seq    SEQUENCE OWNED BY     `   ALTER SEQUENCE my_bank_app.transation_number_seq OWNED BY my_bank_app.transaction.trans_number;
          my_bank_app          postgres    false    212            �            1259    41330    user_name_sel    SEQUENCE     �   CREATE SEQUENCE my_bank_app.user_name_sel
    START WITH 100000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE my_bank_app.user_name_sel;
       my_bank_app          postgres    false    210    11            �           0    0    user_name_sel    SEQUENCE OWNED BY     X   ALTER SEQUENCE my_bank_app.user_name_sel OWNED BY my_bank_app.customer.login_user_name;
          my_bank_app          postgres    false    220            �
           2604    41260    account number    DEFAULT     z   ALTER TABLE ONLY my_bank_app.account ALTER COLUMN number SET DEFAULT nextval('my_bank_app.account_number_seq'::regclass);
 B   ALTER TABLE my_bank_app.account ALTER COLUMN number DROP DEFAULT;
       my_bank_app          postgres    false    219    209            �
           2604    41261    customer id    DEFAULT     x   ALTER TABLE ONLY my_bank_app.customer ALTER COLUMN id SET DEFAULT nextval('my_bank_app.customer_number_seq'::regclass);
 ?   ALTER TABLE my_bank_app.customer ALTER COLUMN id DROP DEFAULT;
       my_bank_app          postgres    false    218    210            �
           2604    41332    customer login_user_name    DEFAULT        ALTER TABLE ONLY my_bank_app.customer ALTER COLUMN login_user_name SET DEFAULT nextval('my_bank_app.user_name_sel'::regclass);
 L   ALTER TABLE my_bank_app.customer ALTER COLUMN login_user_name DROP DEFAULT;
       my_bank_app          postgres    false    220    210            �
           2604    41099    transaction trans_number    DEFAULT     �   ALTER TABLE ONLY my_bank_app.transaction ALTER COLUMN trans_number SET DEFAULT nextval('my_bank_app.transation_number_seq'::regclass);
 L   ALTER TABLE my_bank_app.transaction ALTER COLUMN trans_number DROP DEFAULT;
       my_bank_app          postgres    false    212    213    213            �          0    41006    account 
   TABLE DATA           �   COPY my_bank_app.account (number, account_type, date_created, owner_id, status, approved_by, rejected_by, current_balance) FROM stdin;
    my_bank_app          postgres    false    209            �          0    41117    account_status 
   TABLE DATA           5   COPY my_bank_app.account_status (status) FROM stdin;
    my_bank_app          postgres    false    215   l       �          0    41019    account_type 
   TABLE DATA           �   COPY my_bank_app.account_type (type, monthly_interest, credit_score_req, min_balance_req, overdrawn_amount, annual_service_fee, monthly_fee) FROM stdin;
    my_bank_app          postgres    false    211   C        �          0    41009    customer 
   TABLE DATA             COPY my_bank_app.customer (id, first_name, salutation, dob, address, phone1, phone2, email, basic_checking_acc_id, basic_saving_acc_id, prem_checking_acc_id, prem_saving_acc_id, login_user_name, login_password, credit_score, last_name, ssn, register_by_employee) FROM stdin;
    my_bank_app          postgres    false    210   d        �          0    41156    employee 
   TABLE DATA           R   COPY my_bank_app.employee (id, login_user_name, login_password, name) FROM stdin;
    my_bank_app          postgres    false    216   �       �          0    41096    transaction 
   TABLE DATA           �   COPY my_bank_app.transaction (trans_number, type, withdraw_from, deposit_to, status, time_requested, time_completed, amount, balance_after_withdraw, balance_after_deposit, initiator_acc) FROM stdin;
    my_bank_app          postgres    false    213   5        �          0    41159    transaction_status 
   TABLE DATA           9   COPY my_bank_app.transaction_status (status) FROM stdin;
    my_bank_app          postgres    false    217   �       �          0    41112    transaction_type 
   TABLE DATA           5   COPY my_bank_app.transaction_type (type) FROM stdin;
    my_bank_app          postgres    false    214   0        �           0    0    account_number_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('my_bank_app.account_number_seq', 200026, true);
          my_bank_app          postgres    false    219            �           0    0    customer_number_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('my_bank_app.customer_number_seq', 10015, true);
          my_bank_app          postgres    false    218            �           0    0    transation_number_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('my_bank_app.transation_number_seq', 47, true);
          my_bank_app          postgres    false    212            �           0    0    user_name_sel    SEQUENCE SET     E   SELECT pg_catalog.setval('my_bank_app.user_name_sel', 100000, true);
          my_bank_app          postgres    false    220            �
           2606    41015    account account_pk 
   CONSTRAINT     Y   ALTER TABLE ONLY my_bank_app.account
    ADD CONSTRAINT account_pk PRIMARY KEY (number);
 A   ALTER TABLE ONLY my_bank_app.account DROP CONSTRAINT account_pk;
       my_bank_app            postgres    false    209            �
           2606    41150     account_status account_status_pk 
   CONSTRAINT     g   ALTER TABLE ONLY my_bank_app.account_status
    ADD CONSTRAINT account_status_pk PRIMARY KEY (status);
 O   ALTER TABLE ONLY my_bank_app.account_status DROP CONSTRAINT account_status_pk;
       my_bank_app            postgres    false    215            �
           2606    41023    account_type account_type_pk 
   CONSTRAINT     a   ALTER TABLE ONLY my_bank_app.account_type
    ADD CONSTRAINT account_type_pk PRIMARY KEY (type);
 K   ALTER TABLE ONLY my_bank_app.account_type DROP CONSTRAINT account_type_pk;
       my_bank_app            postgres    false    211            �
           2606    41232 %   customer customer_login_user_name_key 
   CONSTRAINT     p   ALTER TABLE ONLY my_bank_app.customer
    ADD CONSTRAINT customer_login_user_name_key UNIQUE (login_user_name);
 T   ALTER TABLE ONLY my_bank_app.customer DROP CONSTRAINT customer_login_user_name_key;
       my_bank_app            postgres    false    210            �
           2606    41319    customer customer_un_ssn 
   CONSTRAINT     W   ALTER TABLE ONLY my_bank_app.customer
    ADD CONSTRAINT customer_un_ssn UNIQUE (ssn);
 G   ALTER TABLE ONLY my_bank_app.customer DROP CONSTRAINT customer_un_ssn;
       my_bank_app            postgres    false    210            �
           2606    41192    customer customers_pk 
   CONSTRAINT     X   ALTER TABLE ONLY my_bank_app.customer
    ADD CONSTRAINT customers_pk PRIMARY KEY (id);
 D   ALTER TABLE ONLY my_bank_app.customer DROP CONSTRAINT customers_pk;
       my_bank_app            postgres    false    210            �
           2606    41212    employee employee_pk 
   CONSTRAINT     W   ALTER TABLE ONLY my_bank_app.employee
    ADD CONSTRAINT employee_pk PRIMARY KEY (id);
 C   ALTER TABLE ONLY my_bank_app.employee DROP CONSTRAINT employee_pk;
       my_bank_app            postgres    false    216            �
           2606    41101    transaction transation_pk 
   CONSTRAINT     f   ALTER TABLE ONLY my_bank_app.transaction
    ADD CONSTRAINT transation_pk PRIMARY KEY (trans_number);
 H   ALTER TABLE ONLY my_bank_app.transaction DROP CONSTRAINT transation_pk;
       my_bank_app            postgres    false    213            �
           2606    41163 '   transaction_status transation_status_pk 
   CONSTRAINT     n   ALTER TABLE ONLY my_bank_app.transaction_status
    ADD CONSTRAINT transation_status_pk PRIMARY KEY (status);
 V   ALTER TABLE ONLY my_bank_app.transaction_status DROP CONSTRAINT transation_status_pk;
       my_bank_app            postgres    false    217            �
           2606    41123 #   transaction_type transation_type_pk 
   CONSTRAINT     h   ALTER TABLE ONLY my_bank_app.transaction_type
    ADD CONSTRAINT transation_type_pk PRIMARY KEY (type);
 R   ALTER TABLE ONLY my_bank_app.transaction_type DROP CONSTRAINT transation_type_pk;
       my_bank_app            postgres    false    214            �
           2606    41193    account account_fk    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.account
    ADD CONSTRAINT account_fk FOREIGN KEY (owner_id) REFERENCES my_bank_app.customer(id) ON UPDATE CASCADE ON DELETE SET NULL;
 A   ALTER TABLE ONLY my_bank_app.account DROP CONSTRAINT account_fk;
       my_bank_app          postgres    false    209    2791    210            �
           2606    41339    account account_fk_apprby    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.account
    ADD CONSTRAINT account_fk_apprby FOREIGN KEY (approved_by) REFERENCES my_bank_app.employee(id);
 H   ALTER TABLE ONLY my_bank_app.account DROP CONSTRAINT account_fk_apprby;
       my_bank_app          postgres    false    2801    216    209            �
           2606    41348    account account_fk_rejeby    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.account
    ADD CONSTRAINT account_fk_rejeby FOREIGN KEY (rejected_by) REFERENCES my_bank_app.employee(id);
 H   ALTER TABLE ONLY my_bank_app.account DROP CONSTRAINT account_fk_rejeby;
       my_bank_app          postgres    false    2801    216    209            �
           2606    41273    account account_fk_status    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.account
    ADD CONSTRAINT account_fk_status FOREIGN KEY (status) REFERENCES my_bank_app.account_status(status);
 H   ALTER TABLE ONLY my_bank_app.account DROP CONSTRAINT account_fk_status;
       my_bank_app          postgres    false    209    215    2799            �
           2606    41262    account account_fk_type    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.account
    ADD CONSTRAINT account_fk_type FOREIGN KEY (account_type) REFERENCES my_bank_app.account_type(type);
 F   ALTER TABLE ONLY my_bank_app.account DROP CONSTRAINT account_fk_type;
       my_bank_app          postgres    false    211    209    2793            �
           2606    41134    customer customer_fk_bc    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.customer
    ADD CONSTRAINT customer_fk_bc FOREIGN KEY (basic_checking_acc_id) REFERENCES my_bank_app.account(number) ON UPDATE CASCADE ON DELETE SET NULL;
 F   ALTER TABLE ONLY my_bank_app.customer DROP CONSTRAINT customer_fk_bc;
       my_bank_app          postgres    false    2785    210    209            �
           2606    41129    customer customer_fk_bs    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.customer
    ADD CONSTRAINT customer_fk_bs FOREIGN KEY (basic_saving_acc_id) REFERENCES my_bank_app.account(number) ON UPDATE CASCADE ON DELETE SET NULL;
 F   ALTER TABLE ONLY my_bank_app.customer DROP CONSTRAINT customer_fk_bs;
       my_bank_app          postgres    false    210    2785    209            �
           2606    41139    customer customer_fk_pc    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.customer
    ADD CONSTRAINT customer_fk_pc FOREIGN KEY (prem_checking_acc_id) REFERENCES my_bank_app.account(number) ON UPDATE CASCADE ON DELETE SET NULL;
 F   ALTER TABLE ONLY my_bank_app.customer DROP CONSTRAINT customer_fk_pc;
       my_bank_app          postgres    false    210    2785    209            �
           2606    41144    customer customer_fk_ps    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.customer
    ADD CONSTRAINT customer_fk_ps FOREIGN KEY (prem_saving_acc_id) REFERENCES my_bank_app.account(number) ON UPDATE CASCADE ON DELETE SET NULL;
 F   ALTER TABLE ONLY my_bank_app.customer DROP CONSTRAINT customer_fk_ps;
       my_bank_app          postgres    false    2785    209    210            �
           2606    41333    customer customer_fk_rbe    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.customer
    ADD CONSTRAINT customer_fk_rbe FOREIGN KEY (register_by_employee) REFERENCES my_bank_app.employee(id);
 G   ALTER TABLE ONLY my_bank_app.customer DROP CONSTRAINT customer_fk_rbe;
       my_bank_app          postgres    false    210    2801    216                       2606    41233    transaction transaction_fk    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.transaction
    ADD CONSTRAINT transaction_fk FOREIGN KEY (initiator_acc) REFERENCES my_bank_app.account(number);
 I   ALTER TABLE ONLY my_bank_app.transaction DROP CONSTRAINT transaction_fk;
       my_bank_app          postgres    false    2785    213    209            �
           2606    41107    transaction transation_fk_dt    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.transaction
    ADD CONSTRAINT transation_fk_dt FOREIGN KEY (deposit_to) REFERENCES my_bank_app.account(number);
 K   ALTER TABLE ONLY my_bank_app.transaction DROP CONSTRAINT transation_fk_dt;
       my_bank_app          postgres    false    213    209    2785                        2606    41164    transaction transation_fk_ts    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.transaction
    ADD CONSTRAINT transation_fk_ts FOREIGN KEY (status) REFERENCES my_bank_app.transaction_status(status) ON UPDATE CASCADE;
 K   ALTER TABLE ONLY my_bank_app.transaction DROP CONSTRAINT transation_fk_ts;
       my_bank_app          postgres    false    213    217    2803                       2606    41206    transaction transation_fk_ty    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.transaction
    ADD CONSTRAINT transation_fk_ty FOREIGN KEY (type) REFERENCES my_bank_app.transaction_type(type) ON UPDATE CASCADE;
 K   ALTER TABLE ONLY my_bank_app.transaction DROP CONSTRAINT transation_fk_ty;
       my_bank_app          postgres    false    214    213    2797            �
           2606    41102    transaction transation_fk_wf    FK CONSTRAINT     �   ALTER TABLE ONLY my_bank_app.transaction
    ADD CONSTRAINT transation_fk_wf FOREIGN KEY (withdraw_from) REFERENCES my_bank_app.account(number);
 K   ALTER TABLE ONLY my_bank_app.transaction DROP CONSTRAINT transation_fk_wf;
       my_bank_app          postgres    false    2785    213    209           
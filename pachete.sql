--=============================================================================--
--- PACHET PENTRU GESTIONAREA DATELOR DIN BAZA DE DATE IN BOTUL DE PE DISCORD ---

CREATE OR REPLACE PACKAGE discord_bot AS
    function getAllUsers return varchar2;
    function getTopUsers return varchar2;
    function getUserMaxScore(p_username varchar2) return integer;
    function checkIfUserExists(p_username varchar2) return integer;
END discord_bot;
/

CREATE OR REPLACE PACKAGE BODY discord_bot AS

    FUNCTION getAllUsers return varchar2 is
        cursor jucator is select * from useri order by username;
        v_linie_user jucator%rowtype;
        v_text_returnat varchar2(1000);
        v_index_linie_returnata integer := 1;
    begin
        open jucator;
        loop
            fetch jucator into v_linie_user;
            exit when jucator%notfound;
                v_text_returnat := v_text_returnat || v_index_linie_returnata || ' - ' || v_linie_user.username || chr(10);
                v_index_linie_returnata := v_index_linie_returnata + 1;
        end loop;

        return v_text_returnat;
    end;

    function getUserMaxScore(p_username varchar2) return integer is
        v_scor_maxim scoruri.scor%type := 0;
        v_userul_exista integer := 0;
        v_userul_are_scor integer := 0;
    begin
        select count(*) into v_userul_exista from useri where USERNAME = p_username;
        if v_userul_exista != 0 then
            select count(*) into v_userul_are_scor from scoruri where ID_USER = (select id from useri where USERNAME = p_username);
            if v_userul_are_scor != 0 then
                select max(scor) into v_scor_maxim
                    from scoruri where id_user =
                                   (select id from useri where username = p_username);
            end if;
        end if;

        return v_scor_maxim;
    end;

    FUNCTION getTopUsers return varchar2 is
        cursor jucator is select * from useri order by username;
        v_linie_user jucator%rowtype;
        v_scor_maxim_jucator integer;

        v_scor_loc1 scoruri.scor%type := 9999;
        v_scor_loc2 scoruri.scor%type := 9999;
        v_scor_loc3 scoruri.scor%type := 9999;

        v_username_loc1 useri.username%type := '';
        v_username_loc2 useri.username%type := '';
        v_username_loc3 useri.username%type := '';

        v_text_returnat varchar2(1000);
    begin
        open jucator;
        loop
            fetch jucator into v_linie_user;
                v_scor_maxim_jucator := getUserMaxScore(v_linie_user.USERNAME);
                if v_scor_maxim_jucator < v_scor_loc1 then
                    v_scor_loc3 := v_scor_loc2;
                    v_scor_loc2 := v_scor_loc1;
                    v_scor_loc1 := v_scor_maxim_jucator;
                    v_username_loc3 := v_username_loc2;
                    v_username_loc2 := v_username_loc1;
                    v_username_loc1 := v_linie_user.USERNAME;
                else
                    if v_scor_maxim_jucator < v_scor_loc2 then
                        v_scor_loc3 := v_scor_loc2;
                        v_scor_loc2 := v_scor_maxim_jucator;
                        v_username_loc3 := v_username_loc2;
                        v_username_loc2 := v_linie_user.USERNAME;
                    else
                        if v_scor_maxim_jucator < v_scor_loc3 then
                            v_scor_loc3 := v_scor_maxim_jucator;
                            v_username_loc3 := v_linie_user.USERNAME;
                        end if;
                    end if;
                end if;
            exit when jucator%notfound;
        end loop;

        if v_scor_loc1 = 9999 then
            v_scor_loc1 := 0;
        end if;
        if v_scor_loc2 = 9999 then
            v_scor_loc2 := 0;
        end if;
        if v_scor_loc3 = 9999 then
            v_scor_loc3 := 0;
        end if;

        v_text_returnat := v_text_returnat || '1. **' || v_username_loc1 || ' ' || v_scor_loc1 || '**' || chr(10);
        v_text_returnat := v_text_returnat || '2. **' || v_username_loc2 || ' ' || v_scor_loc2 || '**' || chr(10);
        v_text_returnat := v_text_returnat || '3. **' || v_username_loc3 || ' ' || v_scor_loc3 || '**';

        return v_text_returnat;
    end;

    function checkIfUserExists(p_username varchar2) return integer is
        v_exista integer := 0;
    begin
        select count(*) into v_exista from useri where USERNAME = p_username;
        return v_exista;
    end;

END discord_bot;
/

--=======================================================--
--- PACHET PENTRU GESTIONAREA DATELOR PRIMTIE DE LA JOC ---

CREATE OR REPLACE PACKAGE game_pa AS

    procedure insertNewUser(p_username IN varchar2);
    procedure insertUserScore(p_username IN varchar2, p_scor IN integer);
    procedure insertNewLoggedUser(p_username IN varchar2);
    function checkIfUserIsLogged(p_username IN varchar2) return integer;

END game_pa;
/

CREATE OR REPLACE PACKAGE BODY game_pa AS

        procedure insertNewUser(p_username IN varchar2) is
        begin
            insert into useri values(1, p_username);
        end;

        procedure insertUserScore(p_username IN varchar2, p_scor IN integer) is
            v_username_id useri.id%type;
        begin
            select id into v_username_id from useri where USERNAME = p_username;
            insert into scoruri values (1, v_username_id, p_scor, sysdate);
        end;

        procedure insertNewLoggedUser(p_username IN varchar2) is
            v_username_id useri.id%type;
        begin
            select id into v_username_id from useri where USERNAME = p_username;
            insert into USERI_LOGGATI values (1, v_username_id);
        end;

        function checkIfUserIsLogged(p_username IN varchar2) return integer is
            v_esteLogat integer := 0;
            v_username_id useri.id%type;
        begin
            select id into v_username_id from useri where USERNAME = p_username;
            select count(*) into v_esteLogat from USERI_LOGGATI where ID_USER = v_username_id;
            return v_esteLogat;
        end;

END game_pa;
/
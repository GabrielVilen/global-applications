/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.integration;

/**
 *
 * @author Kim
 */
public class SQLQueries {

    public final static String SELECT_USER_LOGIN = "SELECT * FROM ´user´ WHERE ´username´=? AND `password`=?";
    public final static String SELECT_PERSON_ONUSERNAME = "";
    public final static String INSERT_PERSON = "";
    public final static String INSERT_USER = "INSERT INTO `iv1201gasakiproject`.`user`\n"
            + "(`username`,\n"
            + "`password`,\n"
            + "`active`)\n"
            + "VALUES\n"
            + "(<{username: }>,\n"
            + "<{password: }>,\n"
            + "<{active: b'1'}>);\n"
            + "SELECT * FROM iv1201gasakiproject.user";
}

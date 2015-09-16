package com.yipeng.stat211j;

import com.yipeng.stat211j.models.Checker;
import com.yipeng.stat211j.models.Reader;
import com.yipeng.stat211j.utils.Utils;

/**
 * Created by huangyip on 14/09/2015.
 */
public class Main {

    public static void main(String[] args) {
        Checker c = new Checker();
        c.init();

        Reader r = new Reader();
        Utils.console(c.count211(r.read("res/dat/testcase1.xlsx")));
    }
}

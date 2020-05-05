package com.company.models;

import com.company.csp.ChooseFromDomainType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Variable implements Cloneable{
    private static ArrayList<Character> defaultDomain=null;
    private static Character defaultChar;
    private ArrayList<Character> domain;
    private Character value;
    private boolean constant;

    public Object clone() throws CloneNotSupportedException{
        return (Variable) super.clone();
    }

    public static Character getDefaultChar() {
        return defaultChar;
    }

    public static void setDefaultChar(Character defaultChar) {
        Variable.defaultChar = defaultChar;
    }

    public Variable(Character value, ArrayList<Character> domain){
        if(domain == null){
            this.domain = (ArrayList<Character>) defaultDomain.clone();
            constant = false;
        }
        else {
            this.domain = domain;
            constant = true;
        }
        this.value = value;
    }

    public void resetVariable(){
        if(!this.constant) {
            this.value = defaultChar;
            restoreDefaultDomain();
        }
    }

    private void restoreDefaultDomain(){
        this.domain = (ArrayList<Character>) defaultDomain.clone();
    }

    public void setValueFromDomain(int i){
        this.value = domain.get(i);
    }

    public boolean isConstant() {
        return constant;
    }

    private Character getFirstFromDomain(){
        if(this.domain.size() > 0 && !this.isConstant()) {
            return this.domain.remove(0);
        }
        return null;
    }

    private Character getRandomFromDomain(){
        if(this.domain.size() > 0 && !this.isConstant()) {
            Random rand = new Random();
            int i = rand.nextInt(this.domain.size());
            return this.domain.remove(i);
        }
        return null;
    }

    public Character chooseFromDomain(ChooseFromDomainType choose){
        if(choose.equals(ChooseFromDomainType.FIRST)){
            return getFirstFromDomain();
        }
        else if(choose.equals(ChooseFromDomainType.RANDOM)){
            return getRandomFromDomain();
        }
        else{
            return null;
        }
    }

    public static ArrayList<Character> getDefaultDomain() {
        return defaultDomain;
    }

    public ArrayList<Character> getDomain() {
        return domain;
    }

    public void setDomain(ArrayList<Character> domain) {
        this.domain = domain;
    }

    public Character getValue() {

        return value;
    }

    public void setValue(Character value) {
        this.value = value;
        //this.domain = new ArrayList<>(Arrays.asList(value));
    }

    public static void setDefaultDomain(ArrayList<Character> defaultDomain){
        Variable.defaultDomain = defaultDomain;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

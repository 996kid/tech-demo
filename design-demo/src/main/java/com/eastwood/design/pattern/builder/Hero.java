package com.eastwood.design.pattern.builder;

/** builder 模式
 * @author 996kid@gmail.com
 * @Description BuilderDemo
 * @Date 2022/5/9 23:31
 */
public class Hero {

    private final String profession;
    private final String name;
    private final String hairType;
    private final String hairColor;
    private final String armor;
    private final String weapon;

    private Hero(Builder builder) {
        this.profession = builder.profession;
        this.name = builder.name;
        this.hairColor = builder.hairColor;
        this.hairType = builder.hairType;
        this.weapon = builder.weapon;
        this.armor = builder.armor;
    }

    public static class Builder {
        private String profession;
        private String name;
        private String hairType;
        private String hairColor;
        private String armor;
        private String weapon;

        public Builder() {
        }

        public Builder withProfession(String profession) {
            this.profession = profession;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withHairType(String hairType) {
            this.hairType = hairType;
            return this;
        }

        public Builder withHairColor(String hairColor) {
            this.hairColor = hairColor;
            return this;
        }

        public Builder withArmor(String armor) {
            this.armor = armor;
            return this;
        }

        public Builder withWeapon(String weapon) {
            this.weapon = weapon;
            return this;
        }

        public Hero build() {
            /**
             * do some check
             */
            return new Hero(this);
        }
    }
}

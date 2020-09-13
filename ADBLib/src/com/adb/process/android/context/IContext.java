package com.adb.process.android.context;

public abstract class IContext{

    protected StringBuilder builder = new StringBuilder();

    public IContext addExtra(String key, String value){
        builder.append("--es ");
        builder.append(key);
        builder.append(" ");
        builder.append(value);
        builder.append(" ");
        return this;
    }

    public IContext addExtra(String key, boolean value){
        builder.append("--ez ");
        builder.append(key);
        builder.append(" ");
        builder.append(value);
        builder.append(" ");
        return this;
    }

    public IContext addExtra(String key, int value){
        builder.append("--ei ");
        builder.append(key);
        builder.append(" ");
        builder.append(value);
        builder.append(" ");

        return this;
    }

    public IContext addExtra(String key, int[] value) {
        builder.append("--eia ");
        builder.append(key);
        builder.append(" ");
        for(int i = 0;i<value.length;i++){
            builder.append(value[i]);
            if (i < value.length -1){
                builder.append(",");
            }
        }

        builder.append(" ");
        return this;
    }

    public IContext addExtra(String key, Long value){
        builder.append("--el ");
        builder.append(key);
        builder.append(" ");
        builder.append(value);
        builder.append(" ");
        return this;
    }

    public IContext addExtra(String key, Long[] value){
        builder.append("--ela ");
        builder.append(key);
        builder.append(" ");

        for(int i = 0;i<value.length;i++){
            builder.append(value[i]);
            if (i < value.length -1){
                builder.append(",");
            }
        }
        builder.append(" ");
        return this;
    }

    public Intent build(){
        return new Intent(builder.toString());
    }

    public static class Intent{
        public String cmd = "";
        private Intent(String cmd){
            this.cmd = cmd;
        }
    }
}

package base;
import android.content.Context;

import rx.Observable;
public class DataManager {

    private RetrofitService mRetrofitService;
    public DataManager(Context context){
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }
//    public  Observable<Book> getSearchBooks(String name,String tag,int start,int count){
//        return mRetrofitService.getSearchBooks(name,tag,start,count);
//    }
}

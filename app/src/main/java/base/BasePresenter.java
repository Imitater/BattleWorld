package base;


public interface  BasePresenter <V extends BaseView>{
    void attachView(V view);

    void detachView();

    void onCreate();

    void onStart();//暂时没用到

    void onStop();

    void pause();//暂时没用到

}

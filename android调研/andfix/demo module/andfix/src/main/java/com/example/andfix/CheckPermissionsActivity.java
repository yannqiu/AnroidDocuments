package com.example.andfix;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * 继承了Activity，实现Android6.0的运行时权限检测
 * 需要进行运行时权限检测的Activity可以继承这个类
 */
public class CheckPermissionsActivity extends Activity
        implements
			ActivityCompat.OnRequestPermissionsResultCallback {
	/**
	 * 需要进行检测的权限数组
	 */
	protected String[] needPermissions = {
			Manifest.permission.READ_EXTERNAL_STORAGE,
			Manifest.permission.WRITE_EXTERNAL_STORAGE,
			};
	/**
	 * 子Activity传入的perimission
	 */
	protected  String[] mInputPermission;
	
	private static final int PERMISSON_REQUESTCODE = 0;
	
	/**
	 * 判断是否需要检测，防止不停的弹框
	 */
	private boolean isNeedCheck = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/**
		 * 通过bundle获取子Activity传入的permission
		 */
		try {
			mInputPermission = savedInstanceState.getStringArray("permission");
		}catch (NullPointerException e)
		{
			mInputPermission = null;
		}
	}



	@Override
	protected void onResume() {

		super.onResume();
		if(isNeedCheck){
			if(mInputPermission!=null) {
				checkPermissions(mInputPermission);
			}else{
				checkPermissions(needPermissions);
			}
		}

	}

	/**
	 * 
	 * @param permissions
	 * @since 2.5.0
	 *
	 */
	private void checkPermissions(String... permissions) {
		List<String> needRequestPermissonList = findDeniedPermissions(permissions);
		if (!needRequestPermissonList.isEmpty()) {
			ActivityCompat.requestPermissions(this,
					needRequestPermissonList.toArray(
							new String[needRequestPermissonList.size()]),
					PERMISSON_REQUESTCODE);
		}
	}

	/**
	 * 获取权限集中需要申请权限的列表
	 * 
	 * @param permissions
	 * @return
	 * @since 2.5.0
	 *
	 */
	private List<String> findDeniedPermissions(String[] permissions) {
		List<String> needRequestPermissonList = new ArrayList();
		for (String perm : permissions) {
			if (ContextCompat.checkSelfPermission(this,
					perm) != PackageManager.PERMISSION_GRANTED) {
				needRequestPermissonList.add(perm);
			} else {
				if (ActivityCompat.shouldShowRequestPermissionRationale(
						this, perm)) {
					needRequestPermissonList.add(perm);
				} 
			}
		}
		return needRequestPermissonList;
	}

}
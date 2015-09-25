package com.cc.fragmentmgr.fragment.life;

public interface LifeCycleComponent
{
  void onBecomesPartiallyInvisible();

  void onBecomesVisible();

  void onBecomesTotallyInvisible();

  void onBecomesVisibleFromTotallyInvisible();

  void onDestroy();
}
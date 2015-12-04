package com.cc.fragmentmgr.life;

public interface LifeCycleComponent
{
  void onBecomesPartiallyInvisible();

  void onBecomesVisible();

  void onBecomesTotallyInvisible();

  void onBecomesVisibleFromTotallyInvisible();

  void onDestroy();
}
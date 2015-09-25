package com.cc.fragmentmgr.fragment.life;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class LifeCycleComponentManager implements IComponentContainer
{
  private HashMap<String, LifeCycleComponent> mComponentList;

  public static void tryAddComponentToContainer(LifeCycleComponent component, Object matrixContainer)
  {
    tryAddComponentToContainer(component, matrixContainer, true);
  }

  public static boolean tryAddComponentToContainer(LifeCycleComponent component, Object matrixContainer, boolean throwEx) {
    if (matrixContainer instanceof IComponentContainer) {
      ((IComponentContainer)matrixContainer).addComponent(component);
      return true;
    }
    if (throwEx)
      throw new IllegalArgumentException("componentContainerContext should implements IComponentContainer");

    return false;
  }

  public void addComponent(LifeCycleComponent component)
  {
    if (component != null) {
      if (this.mComponentList == null)
        this.mComponentList = new HashMap();

      this.mComponentList.put(component.toString(), component);
    }
  }

  public void onBecomesVisibleFromTotallyInvisible()
  {
    if (this.mComponentList == null) {
      return;
    }

    Iterator it = this.mComponentList.entrySet().iterator();
    while (it.hasNext()) {
      LifeCycleComponent component = (LifeCycleComponent)((Map.Entry)it.next()).getValue();
      if (component != null)
        component.onBecomesVisibleFromTotallyInvisible();
    }
  }

  public void onBecomesTotallyInvisible()
  {
    if (this.mComponentList == null)
      return;

    Iterator it = this.mComponentList.entrySet().iterator();
    while (it.hasNext()) {
      LifeCycleComponent component = (LifeCycleComponent)((Map.Entry)it.next()).getValue();
      if (component != null)
        component.onBecomesTotallyInvisible();
    }
  }

  public void onBecomesPartiallyInvisible()
  {
    if (this.mComponentList == null)
      return;

    Iterator it = this.mComponentList.entrySet().iterator();
    while (it.hasNext()) {
      LifeCycleComponent component = (LifeCycleComponent)((Map.Entry)it.next()).getValue();
      if (component != null)
        component.onBecomesPartiallyInvisible();
    }
  }

  public void onBecomesVisibleFromPartiallyInvisible()
  {
    if (this.mComponentList == null)
      return;

    Iterator it = this.mComponentList.entrySet().iterator();
    while (it.hasNext()) {
      LifeCycleComponent component = (LifeCycleComponent)((Map.Entry)it.next()).getValue();
      if (component != null)
        component.onBecomesVisible();
    }
  }

  public void onDestroy()
  {
    if (this.mComponentList == null)
      return;

    Iterator it = this.mComponentList.entrySet().iterator();
    while (it.hasNext()) {
      LifeCycleComponent component = (LifeCycleComponent)((Map.Entry)it.next()).getValue();
      if (component != null)
        component.onDestroy();
    }
  }
}
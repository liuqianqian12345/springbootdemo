package com.longjing.service.copier;

/**
 * Created by 18746 on 2019/6/3.
 */
public abstract class AbstractCopier<SRC, TAG> {
    abstract TAG copy(SRC src,TAG tag);
}

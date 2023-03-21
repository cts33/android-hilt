package com.example.android.hilt.di

import android.content.Context
import androidx.room.Room
import com.example.android.hilt.data.AppDatabase
import com.example.android.hilt.data.LogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * @description
 * @mail chentaishan@aliyun.com
 * @date 2023/3/19
 * 模块用于向 Hilt 添加绑定，换句话说，用于告知 Hilt 如何提供不同类型的实例。在 Hilt 模块中，您可以为无法通过构造函数注入的类型
 * （例如接口或未包含在您项目中的类）添加绑定。这种类型的一个示例是 OkHttpClient，您需要使用其构建器来创建实例。

Hilt 模块是带有@Module 和 @InstallIn 注解的类。@Module 会告知 Hilt 这是一个模块，而 @InstallIn 会通过指定 Hilt
组件告知 Hilt 绑定在哪些容器中可用。您可以将 Hilt 组件视为容器，如需查看组件的完整列表，请点击此处。
999ikk
对于每个可被 Hilt 注入的 Android 类，都有一个关联的 Hilt 组件。例如，Application 容器与 ApplicationComponent 相关联，
而 Fragment 容器与 FragmentComponent 相关联。
 */
@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule{

    @Provides
    fun provideLogDao(database: AppDatabase): LogDao {
        return database.logDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "logging.db"
        ).build()
    }
}
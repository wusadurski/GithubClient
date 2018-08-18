/**
 * Copyright (C) 2018 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wsadurski.com.githubclient.domain.interactor

import arrow.core.Either
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import wsadurski.com.githubclient.domain.error.ErrorThrowableWrapper

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means than any use
 * case in the application should implement this contract).
 *
 * By convention each [UseCase] implementation will executeSafely its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */

/**
 * Modified version of UseCase class which takes no input parameters
 */
abstract class ArgumentLessUseCase<out Type> where Type : Any {

    abstract suspend fun run(): Either<ErrorThrowableWrapper, Type>

    fun execute(onResult: (Either<ErrorThrowableWrapper, Type>) -> Unit) {
        val job = async(CommonPool) { run() }
        launch(UI) { onResult.invoke(job.await()) }
    }
}
package com.tech.mirzaghalibcollegeuser.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.squareup.picasso.Picasso
import com.tech.mirzaghalibcollegeuser.R
import com.tech.mirzaghalibcollegeuser.adapter.DepartmentAdapter
import java.sql.Timestamp

val hashMap:HashMap<String,String> = HashMap()

class util {

    companion object {

        @RequiresApi(Build.VERSION_CODES.M)
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val activeNetwork =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        }

        fun setTimeStamp(currentMillis:Long): String {

            val timeStamp = Timestamp(currentMillis)
            return timeStamp.toString()
        }
        fun ImageSet(holder: DepartmentAdapter.ViewHolder, departmentItem: String) {

            mapSet()
            Picasso.get().load(hashMap[departmentItem])
                .placeholder(R.drawable.ic_no_image)
                .into(holder.departmentLogo)

        }
        private fun mapSet() {
            hashMap["Botany"] = "https://firebasestorage.googleapis.com/v0/b/mirza-ghalib-college.appspot.com/o/DepartmentPhoto%2Fbotany.png?alt=media&token=da7e43e5-0d74-4c52-a09c-eec3bb190bf8"
            hashMap["Chemistry"]="https://firebasestorage.googleapis.com/v0/b/mirza-ghalib-college.appspot.com/o/DepartmentPhoto%2Fchemistry.jpg?alt=media&token=bc415a5f-9a64-4e72-94b1-28670654efa2"
            hashMap["Zoology"]="https://firebasestorage.googleapis.com/v0/b/mirza-ghalib-college.appspot.com/o/DepartmentPhoto%2Fzoology.jpg?alt=media&token=50266fa6-4808-4990-8198-7b035fbba254"
            hashMap["Physics"]="https://firebasestorage.googleapis.com/v0/b/mirza-ghalib-college.appspot.com/o/DepartmentPhoto%2Fphysics.jpg?alt=media&token=67171725-431d-4f4d-8d1a-acfe8d18431b"
            hashMap["Mathematics"]="https://firebasestorage.googleapis.com/v0/b/mirza-ghalib-college.appspot.com/o/DepartmentPhoto%2FMath.jpg?alt=media&token=b1f4af17-9d89-4198-a3b2-db426314541e"
            hashMap["History"]="https://firebasestorage.googleapis.com/v0/b/mirza-ghalib-college.appspot.com/o/DepartmentPhoto%2Fhistory.jpg?alt=media&token=6fb95170-c03d-4bb3-9b6f-e2eba485c491"
            hashMap["Political Science"]="https://firebasestorage.googleapis.com/v0/b/mirza-ghalib-college.appspot.com/o/DepartmentPhoto%2FPolitical-Science.jpg?alt=media&token=fef6e4a9-9e00-4a23-aaf2-bfdb8b0f4a2c"
            hashMap["Psychology"]="https://firebasestorage.googleapis.com/v0/b/mirza-ghalib-college.appspot.com/o/DepartmentPhoto%2FPsychology.jpg?alt=media&token=9a155ca8-3f78-47e1-9827-0da66ef17a6f"
            hashMap["Economics"]="https://firebasestorage.googleapis.com/v0/b/mirza-ghalib-college.appspot.com/o/DepartmentPhoto%2Fecnomics.jpg?alt=media&token=70036376-6224-485e-919d-1be6a98d8da7"
            hashMap["Geography"]="https://firebasestorage.googleapis.com/v0/b/mirza-ghalib-college.appspot.com/o/DepartmentPhoto%2Fgeography.jpg?alt=media&token=801aef4f-71e7-41a0-9b2e-c34f63349c2d"
            hashMap["Home Science"]="https://firebasestorage.googleapis.com/v0/b/mirza-ghalib-college.appspot.com/o/DepartmentPhoto%2Fhome_science.jpg?alt=media&token=708de9ab-6ed4-48e8-8244-d8b3a6276271"
            hashMap["Sociology"]="https://firebasestorage.googleapis.com/v0/b/mirza-ghalib-college.appspot.com/o/DepartmentPhoto%2Fsociology.jpg?alt=media&token=a0476351-31ee-458f-b885-5b85925984b7"
            hashMap["Commerce"]="https://firebasestorage.googleapis.com/v0/b/mirza-ghalib-college.appspot.com/o/DepartmentPhoto%2FCommerce.jpg?alt=media&token=325b58e8-c413-412d-9406-6deefed2ccd9"
            hashMap["BCA"] = "https://firebasestorage.googleapis.com/v0/b/mirza-ghalib-college.appspot.com/o/DepartmentPhoto%2FBCA.jpg?alt=media&token=032a49aa-89ac-422e-81a3-4bcc463d350b"
            hashMap["BBM"]="https://firebasestorage.googleapis.com/v0/b/mirza-ghalib-college.appspot.com/o/DepartmentPhoto%2FBBm.jpg?alt=media&token=420f0e88-55ca-481a-b504-78dfe995cc9c"
            hashMap["Bio-Tech"]="https://firebasestorage.googleapis.com/v0/b/mirza-ghalib-college.appspot.com/o/DepartmentPhoto%2Fbio_tech.jpg?alt=media&token=7fa24ff7-3bf3-48f4-a1c1-fb142f370c1a"
            hashMap["Urdu"]="https://firebasestorage.googleapis.com/v0/b/mirza-ghalib-college.appspot.com/o/DepartmentPhoto%2FUrdu.png?alt=media&token=c8c66c68-d245-4b9a-8225-cbd9ad5e26da"
            hashMap["English"]="https://firebasestorage.googleapis.com/v0/b/mirza-ghalib-college.appspot.com/o/DepartmentPhoto%2Fenglish.jpg?alt=media&token=679e21aa-a7f7-41b8-80c3-48379717d73e"
            hashMap["Hindi"]="https://firebasestorage.googleapis.com/v0/b/mirza-ghalib-college.appspot.com/o/DepartmentPhoto%2Fhindi.jpg?alt=media&token=da7f6bdb-4a4c-41ea-861d-3b017957d76b"
            hashMap["Philosophy"]="https://firebasestorage.googleapis.com/v0/b/mirza-ghalib-college.appspot.com/o/DepartmentPhoto%2Fphilosophy.jpg?alt=media&token=d7b96dce-5a19-4fe4-9603-001ab47c0c3c"
        }


    }
}
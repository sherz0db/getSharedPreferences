package com.example.testapp

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import com.example.testapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    lateinit var bindingMain:ActivityMainBinding

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        bindingMain.saqlash.setOnClickListener(
            {

                if (bindingMain.email.text.isNotEmpty() && bindingMain.password.text.isNotEmpty()) {
                   val email = bindingMain.email.text.toString()
                   val password = bindingMain.password.text.toString()
                   editor.apply{
                       putString("email",email)
                       putString("password",password)
                       apply()

                   }
                    Toast.makeText(this, "Saqlandi", Toast.LENGTH_SHORT).show()
                }
                else {
                    if (bindingMain.email.text.isEmpty()) {
                        Toast.makeText(this, "Iltimos emailingizni kiriting", Toast.LENGTH_SHORT)
                            .show()
                    } else if (bindingMain.password.text.isEmpty()) {
                        Toast.makeText(this, "Iltimos parol kiriting", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        )

        bindingMain.yuklash.setOnClickListener(
            {
                val email = sharedPref.getString("email",null)
                val password= sharedPref.getString("password",null)
                bindingMain.email.setText(email)
                bindingMain.password.setText(password)

            }
        )

        bindingMain.checkBox.setOnClickListener(
            {
                if(bindingMain.checkBox.isChecked){
                    bindingMain.showHide.text="Hide password"
                    bindingMain.password.transformationMethod=null
                }
                else{
                    bindingMain.showHide.text="Show password"
                    bindingMain.password.transformationMethod=PasswordTransformationMethod.getInstance()
                }
            }
        )


    }


}
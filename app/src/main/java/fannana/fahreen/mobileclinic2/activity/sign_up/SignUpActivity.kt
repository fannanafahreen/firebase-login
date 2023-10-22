package fannana.fahreen.mobileclinic2.activity.sign_up

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import fannana.fahreen.mobileclinic2.activity.log_in.LogInActivity
import fannana.fahreen.mobileclinic2.databinding.SignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: SignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = SignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

     //   FirebaseApp.initializeApp(this)

        binding.btnSignup.setOnClickListener {
            val name = binding.etUserName.text.toString()
            val phone = binding.etUserPhone.text.toString()
            val address = binding.etUserAddress.text.toString()
            val email = binding.etUserMail.text.toString()
            val pass = binding.etPassword.text.toString()
            val confirmPass = binding.etPasswordConfirm.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {

                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, LogInActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                        }
                    }
                } else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
package sg.edu.rp.c346.id19045104.c346_billcal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    TextView bill;
    TextView paxpay;
    EditText amount;
    EditText pax;
    EditText discount;
    Button spilt;
    Button reset;
    ToggleButton svs;
    ToggleButton gst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bill =findViewById(R.id.TotalBill); // Total Bill

        paxpay = findViewById(R.id.EachPay); // pay per pax

        amount = findViewById(R.id.editAmount); // input amount

        pax = findViewById(R.id.editPax); // inputpax

        discount = findViewById(R.id.editDiscount); // inputdiscount

        spilt = findViewById(R.id.Split); // spilt button
        reset = findViewById(R.id.Reset); // reset button

        //spilt button
        spilt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(amount.getText().toString().trim().length()!=0 && pax.getText().toString().trim().length()!=0){
                   double newamt= 0;
                   if(!svs.isChecked() && !gst.isChecked()){
                       newamt = Double.parseDouble(amount.getText().toString());
                   } else if(svs.isChecked() && !gst.isChecked()){
                       newamt = Double.parseDouble(amount.getText().toString()) * 1.1;

                   } else if(!svs.isChecked() && gst.isChecked()){
                       newamt = Double.parseDouble((amount.getText().toString())) * 1.07;

                   }else{
                       newamt = Double.parseDouble(amount.getText().toString()) * 1.17;
                   }
                   //Discount
                   if(discount.getText().toString().trim().length()!=0){
                       newamt *=1 - Double.parseDouble(discount.getText().toString()) / 100;

                   }
                   bill.setText("Total Bill: $" + String.format("%.2f",newamt));
                   int numperson = Integer.parseInt(pax.getText().toString());
                   if(numperson !=1){
                        paxpay.setText("Each Pay: $" + String.format("%.2f",newamt / numperson));
                   } else{
                       paxpay.setText("Each Pay: $" + newamt);
                   }
               }


            }
        });
        // reset button
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svs.setChecked(false);
                gst.setChecked(false);

            }
        });

    }
}

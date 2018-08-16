package save.com.instigatemobile.gsonparser.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import save.com.instigatemobile.gsonparser.MapsActivity;
import save.com.instigatemobile.gsonparser.R;
import save.com.instigatemobile.gsonparser.api.Result;

public class MultiViewTypeAdapter extends RecyclerView.Adapter {

    private List<Result> mlist;
    private Context mContext;

    class FemaleViewHolder extends RecyclerView.ViewHolder {

        CircleImageView mImageFemale;
        TextView mNameFemale;
        ImageView mEmailIcon;
        ImageView mPhoneIcon;
        String mLongitudeFemale;
        String mLatitudeFemale;

        String phoneNumberFemale;
        String emailAdressFemale;

        FemaleViewHolder(final View itemView) {
            super(itemView);
            this.mNameFemale = itemView.findViewById(R.id.title_female);
            this.mImageFemale = itemView.findViewById(R.id.image_female);
            this.mPhoneIcon = itemView.findViewById(R.id.call_image);
            this.mEmailIcon = itemView.findViewById(R.id.email_image);

            mPhoneIcon.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + phoneNumberFemale));
                    mContext.startActivity(intent);
                }
            });

            mEmailIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", emailAdressFemale, null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, emailAdressFemale);
                    mContext.startActivity(Intent.createChooser(emailIntent, "Send email"));
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, MapsActivity.class);
                    intent.putExtra("coordinateX", mLatitudeFemale);
                    intent.putExtra("coordinateY", mLongitudeFemale);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    class MaleViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView mImageMale;
        private TextView mNameMale;

        String mLatitudeMmale;
        String mLongitudeMale;

        MaleViewHolder(View itemView) {
            super(itemView);
            this.mImageMale = itemView.findViewById(R.id.image_male);
            this.mNameMale = itemView.findViewById(R.id.title_male);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, MapsActivity.class);
                    intent.putExtra("coordinateX", mLatitudeMmale);
                    intent.putExtra("coordinateY", mLongitudeMale);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    public MultiViewTypeAdapter(List<Result> data, Context context) {
        this.mlist = data;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.female_type, parent, false);
                return new FemaleViewHolder(view);
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.male_type, parent, false);
                return new MaleViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        Result result = mlist.get(position);
        switch (result.getGender()) {
            case "male":
                return 0;
            case "female":
                return 1;
            default:
                return -1;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int listPosition) {

        Result result = mlist.get(listPosition);
        switch (holder.getItemViewType()) {
            case 0:
                MaleViewHolder maleViewHolder = (MaleViewHolder) holder;
                (maleViewHolder).mNameMale.setText(result.getName().getFirst());
                Picasso.get().load(result.getPicture().getThumbnail()).fit().into(maleViewHolder.mImageMale);
                (maleViewHolder).mLatitudeMmale = result.getLocation().getCoordinates().getLatitude();
                (maleViewHolder).mLongitudeMale = result.getLocation().getCoordinates().getLongitude();
                break;
            case 1:
                FemaleViewHolder femaleViewHolder = (FemaleViewHolder) holder;
                (femaleViewHolder).mNameFemale.setText(result.getName().getFirst());
                Picasso.get().load(result.getPicture().getThumbnail()).into(femaleViewHolder.mImageFemale);
                (femaleViewHolder).phoneNumberFemale = result.getPhone();
                (femaleViewHolder).emailAdressFemale = result.getEmail();
                (femaleViewHolder).mLongitudeFemale = result.getLocation().getCoordinates().getLongitude();
                (femaleViewHolder).mLatitudeFemale = result.getLocation().getCoordinates().getLatitude();
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void setData(List<Result> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();
    }
}


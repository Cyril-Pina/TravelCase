import SwiftUI

struct TripCard: View {
    let title: String
    let subtitle: String
    let countryIntent: CountryIntent?
    let fileCount: Int
    let bannerUri: String
    let onClick: () -> Void
    let tripStepIntent: TripStepIntent?
    
    init(
        title: String,
        subtitle: String,
        countryIntent: CountryIntent?,
        fileCount: Int,
        bannerUri: String,
        onClick: @escaping () -> Void,
        tripStepIntent: TripStepIntent? = nil
    ) {
        self.title = title
        self.subtitle = subtitle
        self.countryIntent = countryIntent
        self.fileCount = fileCount
        self.bannerUri = bannerUri
        self.onClick = onClick
        self.tripStepIntent = tripStepIntent
    }
    
    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            ZStack(alignment: .bottomLeading) {
                if let url = getImageURL(uri: bannerUri),
                   let image = UIImage(contentsOfFile: url.path) {
                    GeometryReader { geo in
                        Image(uiImage: image)
                            .resizable()
                            .aspectRatio(contentMode: .fill)
                            .frame(width: geo.size.width, height: 248)
                            .clipped()
                            .clipShape(RoundedRectangle(cornerRadius: 24))
                    }
                    .frame(height: 248)
                } else {
                    Color.gray
                        .frame(height: 248)
                        .clipShape(RoundedRectangle(cornerRadius: 24))
                }
                
                TripCardBadges(
                    countryIntent: countryIntent,
                    fileCount: fileCount,
                    tripStepIntent: tripStepIntent
                )
                .padding(16)
            }
            
            Spacer()
                .frame(height: 8)
            
            Text(
                text: title,
                intent: .h3
            )
            .lineLimit(2)
            .truncationMode(.tail)
            .padding(.horizontal, 16)
            
            Text(
                text: subtitle,
                intent: .smallDim
            )
            .padding(.horizontal, 16)
            
            Spacer()
                .frame(height: 16)
        }
        .padding(8)
        .onTapGesture {
            onClick()
        }
        .frame(maxWidth: .infinity)
        .background(Color.white)
        .clipShape(RoundedRectangle(cornerRadius: 32))
    }
}

private struct TripCardBadges: View {
    let countryIntent: CountryIntent?
    let fileCount: Int
    let tripStepIntent: TripStepIntent?
    
    var body: some View {
        FlowLayout(spacing: 4) {
            if let intent = tripStepIntent {
                TripStepBadge(intent: intent)
            }
            
            if let intent = countryIntent {
                CountryBadge(intent: intent)
            }
            
            FileCountBadge(count: fileCount)
        }
    }
}

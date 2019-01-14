/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DtlAppMockTestModule } from '../../../test.module';
import { BankcardInfoComponent } from 'app/entities/bankcard-info/bankcard-info.component';
import { BankcardInfoService } from 'app/entities/bankcard-info/bankcard-info.service';
import { BankcardInfo } from 'app/shared/model/bankcard-info.model';

describe('Component Tests', () => {
    describe('BankcardInfo Management Component', () => {
        let comp: BankcardInfoComponent;
        let fixture: ComponentFixture<BankcardInfoComponent>;
        let service: BankcardInfoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [BankcardInfoComponent],
                providers: []
            })
                .overrideTemplate(BankcardInfoComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BankcardInfoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BankcardInfoService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new BankcardInfo(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.bankcardInfos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
